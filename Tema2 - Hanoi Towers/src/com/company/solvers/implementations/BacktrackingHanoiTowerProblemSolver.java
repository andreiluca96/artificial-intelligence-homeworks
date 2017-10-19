package com.company.solvers.implementations;

import com.company.problems.State;
import com.company.solvers.abstracts.HanoiTowerProblemSolverImpl;
import com.company.solvers.exceptions.StuckAlgorithmException;

import java.util.*;

/**
 * Created by Luca Andrei on 10/19/2017.
 */
public class BacktrackingHanoiTowerProblemSolver extends HanoiTowerProblemSolverImpl {
    private List<State> visitedStates = new ArrayList<>();
    private Stack<State> stateStack = new Stack<>();

    @Override
    protected void applyTransition(State currentState) throws StuckAlgorithmException {
        State oldState = new State(currentState);
        visitedStates.add(oldState);
        stateStack.push(oldState);

        for (int i = 0; i < problem.getNumberOfDiscs(); i++) {
            for (int j = 0; j < problem.getNumberOfRods(); j++) {
                if (currentState.move(i, j) && !visitedStates.contains(currentState)) {
                    return;
                } else {
                    currentState.setStateRepresentation(new ArrayList<>(oldState.getStateRepresentation()));
                }
            }
        }
        stateStack.pop();
        currentState.setStateRepresentation(stateStack.peek().getStateRepresentation());
        stateStack.pop();
    }
}
