package com.company.solvers.implementations;

import com.company.problems.State;
import com.company.solvers.abstracts.HanoiTowerProblemSolverImpl;
import com.company.solvers.exceptions.StuckAlgorithmException;

import java.util.Random;

/**
 * Created by Luca Andrei on 10/18/2017.
 */
public class TotalRandomHanoiTowerProblemSolver extends HanoiTowerProblemSolverImpl {
    private static int MAX_NUMBER_OF_TRIES = 1000;

    @Override
    protected void applyTransition(State currentState) throws StuckAlgorithmException {
        Random random = new Random();
        int chosenFromRod = random.nextInt(problem.getNumberOfRods());

        while(currentState.getTopDisc(chosenFromRod) == -1) {
            chosenFromRod = random.nextInt(problem.getNumberOfRods());
        }

        int numberOfTries = 0;
        int chosenToRod = random.nextInt(problem.getNumberOfRods());
        while(!currentState.move(currentState.getTopDisc(chosenFromRod), chosenToRod)) {
            chosenToRod = random.nextInt(problem.getNumberOfRods());

            numberOfTries++;
            if (numberOfTries > MAX_NUMBER_OF_TRIES) {
                throw new StuckAlgorithmException("The algorithm got stuck!");
            }
        }
    }
}
