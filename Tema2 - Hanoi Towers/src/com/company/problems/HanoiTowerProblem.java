package com.company.problems;


import static com.google.common.base.Preconditions.*;

/**
 * Created by Luca Andrei on 10/18/2017.
 */
public class HanoiTowerProblem {
    private int numberOfDiscs;
    private int numberOfRods;

    public HanoiTowerProblem(int numberOfDiscs, int numberOfRods) {
        checkArgument(numberOfRods >= 3, "The number of rods should be greater than 3.");
        checkArgument(numberOfDiscs >= 1, "The number of discs should be greater than 1.");

        this.numberOfDiscs = numberOfDiscs;
        this.numberOfRods = numberOfRods;
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
}
