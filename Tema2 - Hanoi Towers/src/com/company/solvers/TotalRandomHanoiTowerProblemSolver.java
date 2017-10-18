package com.company.solvers;

import com.company.problems.HanoiTowerProblem;
import com.company.problems.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Luca Andrei on 10/18/2017.
 */
public class TotalRandomHanoiTowerProblemSolver implements HanoiTowerProblemSolver {

    private List<State> gameHistory = new ArrayList<>();

    @Override
    public List<State> solve(HanoiTowerProblem problem) {
        State currentState = new State(problem.getNumberOfDiscs(), problem.getNumberOfRods());

        Random random = new Random();
        while (!currentState.isFinal()) {
            State oldState = new State(currentState);
            gameHistory.add(oldState);

            int chosenFromRod = random.nextInt(problem.getNumberOfRods());
            while(currentState.getTopDisc(chosenFromRod) == -1) {
                chosenFromRod = random.nextInt(problem.getNumberOfRods());
            }

            int chosenToRod = random.nextInt(problem.getNumberOfRods());
            while(!currentState.move(currentState.getTopDisc(chosenFromRod), chosenToRod)) {
                chosenToRod = random.nextInt(problem.getNumberOfRods());
            }
        }

        return gameHistory;
    }
}
