package com.company.problems;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by Luca Andrei on 10/18/2017.
 */
public class State {
    private int numberOfDiscs;
    private int numberOfRods;

    private List<Integer> stateRepresentation;

    public State(State state) {
        this.numberOfDiscs = state.getNumberOfDiscs();
        this.numberOfRods = state.getNumberOfRods();
        this.stateRepresentation = new ArrayList<>(state.stateRepresentation);
    }

    public State(int numberOfDiscs, int numberOfRods) {
        checkArgument(numberOfRods >= 3, "The number of rods should be greater than 3.");
        checkArgument(numberOfDiscs >= 1, "The number of discs should be greater than 1.");

        stateRepresentation = new ArrayList<>();
        for (int i = 0; i < numberOfDiscs; i++) {
            stateRepresentation.add(0);
        }

        this.numberOfDiscs = numberOfDiscs;
        this.numberOfRods = numberOfRods;
    }

    public int getTopDisc(int rod) {
        for (int i = 0; i < numberOfDiscs; i++) {
            if (stateRepresentation.get(i).equals(rod)) {
                return i;
            }
        }

        return -1;
    }

    public boolean move(int disc, int rod) {
        if (stateRepresentation.get(disc).equals(rod)) {
            return false;
        }

        for (int i = disc - 1; i >= 0; i--) {
            if (stateRepresentation.get(disc).equals(stateRepresentation.get(i)) ||
                    stateRepresentation.get(i).equals(rod)){
                return false;
            }
        }

        stateRepresentation.set(disc, rod);

        return true;
    }

    public boolean isFinal() {
        for (int i = 0; i < numberOfDiscs; i++) {
            if (!stateRepresentation.get(i).equals(numberOfRods - 1)) {
                return false;
            }
        }

        return true;
    }

    public int getNumberOfDiscs() {
        return numberOfDiscs;
    }

    public void setNumberOfDiscs(int numberOfDiscs) {
        this.numberOfDiscs = numberOfDiscs;
    }

    public int getNumberOfRods() {
        return numberOfRods;
    }

    public void setNumberOfRods(int numberOfRods) {
        this.numberOfRods = numberOfRods;
    }

    public int getRod(int disc) {
        return stateRepresentation.get(disc);
    }

    @Override
    public String toString() {
        return String.valueOf(stateRepresentation);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
