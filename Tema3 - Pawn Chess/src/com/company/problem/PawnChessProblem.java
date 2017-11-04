package com.company.problem;

/**
 * Created by Ariana on 11/4/2017.
 */
public class PawnChessProblem {
    private int numberOfWhitePawns;
    private int numberOfBlackPawns;

    public PawnChessProblem(int numberOfWhitePawns, int numberOfBlackPawns) {
        this.numberOfWhitePawns = numberOfWhitePawns;
        this.numberOfBlackPawns = numberOfBlackPawns;
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
}
