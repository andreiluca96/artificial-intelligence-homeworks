package com.company.solvers;

import com.company.problems.State;

import java.util.Random;

/**
 * Created by Luca Andrei on 10/18/2017.
 */
public class TotalRandomHanoiTowerProblemSolver extends HanoiTowerProblemSolverImpl {
    @Override
    protected void applyTransition(State currentState) {
        Random random = new Random();

        int chosenFromRod = random.nextInt(problem.getNumberOfRods());
        while(currentState.getTopDisc(chosenFromRod) == -1) {
            chosenFromRod = random.nextInt(problem.getNumberOfRods());
        }

        int chosenToRod = random.nextInt(problem.getNumberOfRods());
        while(!currentState.move(currentState.getTopDisc(chosenFromRod), chosenToRod)) {
            chosenToRod = random.nextInt(problem.getNumberOfRods());
        }
    }
}
