package com.company.problem;
import java.util.List;
/**
 * Created by Ariana on 11/4/2017.
 */
public class State {
    private int numberOfWhitePawns;
    private int numberOfBlackPawns;
    public List<Integer> stateListWhitePawns;
    public List<Integer> stateListBlackPawns;

    public State(State state) {
        this.numberOfBlackPawns = state.numberOfBlackPawns;
        this.numberOfWhitePawns = state.numberOfWhitePawns;
        this.stateListBlackPawns = state.stateListBlackPawns;
        this.stateListWhitePawns = state.stateListWhitePawns;
    }

    public State(int numberOfWhitePawns, int numberOfBlackPawns) {
        this.setInitialState();
        this.numberOfWhitePawns = numberOfWhitePawns;
        this.numberOfBlackPawns = numberOfBlackPawns;
    }

    public void setInitialState() {
        for (int i = 0; i < 8; i++) {
            stateListWhitePawns.add(0);
            stateListBlackPawns.add(0);
        }
    }

    public List<State> getPossibleNextMoves() {
        for (int i = 0; i<8; i++) {

        }
    }

    public boolean move(int pawn, int postion) {
        if (stateListBlackPawns.contains(postion)) {
            return false;
        }
        for (int i = 0; i < 8; i++)
        {

        }
    }

    public int getNumberOfWhitePawns() {
        return numberOfWhitePawns;
    }

    public void setNumberOfWhitePawns(int numberOfWhitePawns) {
        this.numberOfWhitePawns = numberOfWhitePawns;
    }

    public int getNumberOfBlackPawns() {
        return numberOfBlackPawns;
    }

    public void setNumberOfBlackPawns(int numberOfBlackPawns) {
        this.numberOfBlackPawns = numberOfBlackPawns;
    }

    public List<Integer> getStateListWhitePawns() {
        return stateListWhitePawns;
    }

    public void setStateListWhitePawns(List<Integer> stateListWhitePawns) {
        this.stateListWhitePawns = stateListWhitePawns;
    }

    public List<Integer> getStateListBlackPawns() {
        return stateListBlackPawns;
    }

    public void setStateListBlackPawns(List<Integer> stateListBlackPawns) {
        this.stateListBlackPawns = stateListBlackPawns;
    }




}
