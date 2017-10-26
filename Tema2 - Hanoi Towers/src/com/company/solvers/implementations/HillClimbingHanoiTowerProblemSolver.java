package com.company.solvers.implementations;

import com.company.problems.State;
import com.company.solvers.abstracts.HanoiTowerProblemSolverImpl;
import com.company.solvers.exceptions.StuckAlgorithmException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Luca Andrei on 10/19/2017.
 */
public class HillClimbingHanoiTowerProblemSolver extends HanoiTowerProblemSolverImpl {
    private int chosenDisc;
    private int chosenRod;
    private double currentFitness;
    private double oldFitness;
    Random random = new Random();
    private List<State> visitedStates = new ArrayList<>();
    private Stack<State> stateStack = new Stack<>();

    @Override
    protected void applyTransition(State currentState) throws StuckAlgorithmException {
        State oldState = new State(currentState);
        stateStack.push(oldState);
        int limit = 0;
        while (limit != 100) {
            chosenDisc = random.nextInt(problem.getNumberOfDiscs());
            chosenRod = random.nextInt(problem.getNumberOfRods());
            if (currentState.move(chosenDisc,chosenRod) && !visitedStates.contains((currentState))) {
                oldFitness = calculateFitness(oldState.getStateRepresentation());
                currentFitness = calculateFitness(currentState.getStateRepresentation());
                visitedStates.add(new State(currentState));
                if (currentFitness <= oldFitness) {
                    return;
                }
                else {
                    currentState.setStateRepresentation(new ArrayList<>(oldState.getStateRepresentation()));
                }
            }
            limit++;
        }
        stateStack.pop();
        currentState.setStateRepresentation(stateStack.peek().getStateRepresentation());
        stateStack.pop();
    }

    public double calculateFitness(List<Integer> solution) {
        double fitness = 0;
        for (int i = 0; i < problem.getNumberOfDiscs(); i++) {
            if(solution.get(i)< problem.getNumberOfRods()) {
                fitness += 1;
            } else {
                for(int j = 0; j<i; j++ ) {
                    if( solution.get(i)<solution.get(j)) {
                        fitness += 2;
                    }
                }
            }
            fitness = 1/(double)(fitness);

        }
        return fitness;
    }
}
