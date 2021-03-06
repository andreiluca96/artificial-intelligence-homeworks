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
        this.whitePawns = new ArrayList<>(state.whitePawns);
        this.blackPawns = new ArrayList<>(state.blackPawns);
    }

    public State(int dimension) {
        for (int i = 0;i<dimension; i++) {
            whitePawns.add(new Pawn(1,i));
            blackPawns.add(new Pawn(6,i));
        }
        this.dimension = dimension;
    }

    public boolean moveWhite(Pawn oldPawn , Pawn newPawn) {
        if (!validatePosition(newPawn) || !whitePawns.contains(oldPawn)) {
            return false;
        }

        if (oldPawn.getLine() == 1
                && oldPawn.getColumn() == newPawn.getColumn()
                && newPawn.getLine() == 3
                && !whitePawns.contains(newPawn)
                && !blackPawns.contains(newPawn)){
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
                && newPawn.getLine() == 4
                && !whitePawns.contains(newPawn)
                && !blackPawns.contains(newPawn)) {
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
        return newPawn.getColumn() < dimension && newPawn.getLine() < dimension
                && newPawn.getColumn() >= 0 && newPawn.getLine() >= 0;
    }

    public boolean isChosenPawnValid (Pawn pawn)
    {
        if (!blackPawns.contains(pawn)) {
            return false;
        }
        return true;
    }

    public List<State> generatePossibleState(boolean isWhitePawn) {
        List<State> possibleStates = new ArrayList<>();
        if(isWhitePawn) {
            for(Pawn pawn : whitePawns) {
                for(int i=0; i< 3; i++) {
                    State newState = new State(this);
                    if(newState.moveWhite(pawn, new Pawn(pawn.getLine() + 1, pawn.getColumn() + i - 1))) {
                        possibleStates.add(newState);
                    }
                }

                if(pawn.getLine() == 1) {
                    State newState = new State(this);
                    newState.moveWhite(pawn, new Pawn(3, pawn.getColumn()));
                    possibleStates.add(newState);
                }
            }
            return possibleStates;
        } else {
            for(Pawn pawn : blackPawns) {
                for(int i=0; i< 3; i++) {
                    State newState = new State(this);
                    if(newState.moveBlack(pawn, new Pawn(pawn.getLine()-1, pawn.getColumn()+i-1))) {
                        possibleStates.add(newState);
                    }
                }
                if(pawn.getLine() == 6) {
                    State newState = new State(this);
                    newState.moveWhite(pawn, new Pawn(4, pawn.getColumn()));
                    possibleStates.add(newState);
                }
            }
        }
        return possibleStates;
    }

    public  boolean isFinalState() {
        for (int i = 0; i < dimension; i++) {
            if(whitePawns.contains(new Pawn(7,i)) || blackPawns.contains(new Pawn(0,i))) {
                return true;
            }
        }
        return this.generatePossibleState(true).isEmpty()
                || (this.generatePossibleState(false).isEmpty());
    }

    public int getWinner() {
        if(this.isFinalState()) {
            for (int i = 0; i< dimension; i++) {
                if (whitePawns.contains(new Pawn(7, i))) {
                    return 1;
                }
                if (blackPawns.contains(new Pawn(0, i))) {
                    return -1;
                }
            }
            return 0;
        }
        throw new IllegalStateException();
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

    public void printState() {
        char [][] matrix = new char[9][9];

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (i == 8) {
                    matrix[i][j] = String.valueOf(j).charAt(0);
                    continue;
                }
                if (j == 0) {
                    matrix[i][j] = String.valueOf(i + 1).charAt(0);
                    continue;
                }

                matrix[i][j] = '0';
            }
        }

        for (Pawn pawn : this.getWhitePawns()) {
            matrix[pawn.getLine()][pawn.getColumn() + 1] = 'W';
        }
        for (Pawn pawn : this.getBlackPawns()) {
            matrix[pawn.getLine()][pawn.getColumn() + 1] = 'B';
        }


        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.print("\n\n");
    }
}
