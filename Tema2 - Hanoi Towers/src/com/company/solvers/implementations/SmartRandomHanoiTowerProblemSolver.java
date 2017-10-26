package com.company.solvers.implementations;

import com.company.problems.State;
import com.company.solvers.abstracts.HanoiTowerProblemSolverImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Luca Andrei on 10/19/2017.
 */
public class SmartRandomHanoiTowerProblemSolver extends HanoiTowerProblemSolverImpl {
    private List<State> visitedStates = new ArrayList<>();
    private Stack<State> stateStack = new Stack<>();
    private int chosenDisc;
    private int chosenRod;
    Random random = new Random();

    @Override
    protected void applyTransition(State currentState) {
        State oldState = new State(currentState);
        visitedStates.add(oldState);
        stateStack.push(oldState);

        int i = 0;

        while (i != 250){
            chosenDisc = random.nextInt(problem.getNumberOfDiscs());
            chosenRod = random.nextInt(problem.getNumberOfRods());
            if (currentState.move(chosenDisc, chosenRod) && !visitedStates.contains(currentState)) {
                return;
            } else {
                currentState.setStateRepresentation(new ArrayList<>(oldState.getStateRepresentation()));
            }
            i++;
        }
        stateStack.pop();
        currentState.setStateRepresentation(stateStack.peek().getStateRepresentation());
        stateStack.pop();
    }
}
