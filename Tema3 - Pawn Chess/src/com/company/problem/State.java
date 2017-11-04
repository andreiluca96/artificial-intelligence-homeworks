package com.company.problem;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by Ariana on 11/4/2017.
 */
public class State {
    private int dimension;
    private List<Pawn> whitePawns = new ArrayList<>();
    private List<Pawn> blackPawns = new ArrayList<>();

    public State(State state) {
        this.dimension = state.dimension;
        this.whitePawns = state.whitePawns;
        this.blackPawns = state.blackPawns;
    }

    public State(int dimension, List<Pawn> whitePawns, List<Pawn> blackPawns) {
        this.dimension = dimension;
        this.whitePawns = whitePawns;
        this.blackPawns = blackPawns;
    }

    public boolean moveWhite(Pawn oldPawn , Pawn newPawn) {
        if (!validatePosition(newPawn) || !whitePawns.contains(oldPawn)) {
            return false;
        }

        if (oldPawn.getLine() == 1
                && oldPawn.getColumn() == newPawn.getColumn()
                && newPawn.getColumn() == 3) {
            whitePawns.remove(oldPawn);
            whitePawns.add(newPawn);

            return true;
        }

        if (oldPawn.getColumn() == newPawn.getColumn()
                && newPawn.getLine() - oldPawn.getLine() == 1
                && !whitePawns.contains(newPawn)
                && !blackPawns.contains(newPawn)) {
            whitePawns.remove(oldPawn);
            whitePawns.add(newPawn);

            return true;
        }

        if (newPawn.getLine() - oldPawn.getLine() == 1
                && abs(newPawn.getColumn() - oldPawn.getColumn()) == 1
                && blackPawns.contains(newPawn)) {
            whitePawns.remove(oldPawn);
            whitePawns.add(newPawn);

            blackPawns.remove(newPawn);

            return true;
        }


        return false;
    }

    public boolean moveBlack(Pawn oldPawn , Pawn newPawn) {
        if (!validatePosition(newPawn) || !blackPawns.contains(oldPawn)) {
            return false;
        }

        if (oldPawn.getLine() == 6
                && oldPawn.getColumn() == newPawn.getColumn()
                && newPawn.getColumn() == 4) {
            blackPawns.remove(oldPawn);
            blackPawns.add(newPawn);

            return true;
        }

        if (oldPawn.getColumn() == newPawn.getColumn()
                && oldPawn.getLine() - newPawn.getLine() == 1
                && !whitePawns.contains(newPawn)
                && !blackPawns.contains(newPawn)) {
            blackPawns.remove(oldPawn);
            blackPawns.add(newPawn);

            return true;
        }

        if (oldPawn.getLine() - newPawn.getLine() == 1
                && abs(oldPawn.getColumn() - newPawn.getColumn()) == 1
                && whitePawns.contains(newPawn)) {
            blackPawns.remove(oldPawn);
            blackPawns.add(newPawn);

            whitePawns.remove(newPawn);

            return true;
        }


        return false;
    }

    private boolean validatePosition(Pawn newPawn) {
        if (newPawn.getColumn() >= dimension || newPawn.getLine() >= dimension
                || newPawn.getColumn() < 0 || newPawn.getLine() < 0) {
            return false;
        }
        return true;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<Pawn> getWhitePawns() {
        return whitePawns;
    }

    public void setWhitePawns(List<Pawn> whitePawns) {
        this.whitePawns = whitePawns;
    }

    public List<Pawn> getBlackPawns() {
        return blackPawns;
    }

    public void setBlackPawns(List<Pawn> blackPawns) {
        this.blackPawns = blackPawns;
    }
}
