package com.company.solvers;

import com.company.problems.HanoiTowerProblem;
import com.company.problems.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luca Andrei on 10/19/2017.
 */
public abstract class HanoiTowerProblemSolverImpl implements HanoiTowerProblemSolver {
    private List<State> gameHistory = new ArrayList<>();

    protected HanoiTowerProblem problem;

    @Override
    public List<State> solve(HanoiTowerProblem problem) {
        this.problem = problem;
        State currentState = new State(problem.getNumberOfDiscs(), problem.getNumberOfRods());

        while (!currentState.isFinal()) {
            State oldState = new State(currentState);
            gameHistory.add(oldState);

            applyTransition(currentState);
        }

        gameHistory.add(currentState);
        return gameHistory;
    }

    protected abstract void applyTransition(State currentState);
}
