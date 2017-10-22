package com.company.solvers.implementations;

import com.company.problems.State;
import com.company.solvers.abstracts.HanoiTowerProblemSolverImpl;
import com.company.solvers.exceptions.StuckAlgorithmException;

import java.util.List;
import java.util.Random;

/**
 * Created by Luca Andrei on 10/19/2017.
 */
public class HillClimbingHanoiTowerProblemSolver extends HanoiTowerProblemSolverImpl {
    private State currentState;
    private State generatedState;
    private int numberOfDiscs;
    private int numberOfRods;
    private int chosenDisc;
    private int chosenRod;
    private int currentFitness;
    private int generatedFitness;
    Random random;

    @Override
    protected void applyTransition(State currentState) throws StuckAlgorithmException {
        currentState.generateInitialState(numberOfDiscs, numberOfRods);
        generatedState.generateInitialState(numberOfDiscs, numberOfRods);
        while(!currentState.isFinal()){
            chosenDisc=random.nextInt(problem.getNumberOfDiscs());
            chosenRod=random.nextInt(problem.getNumberOfRods());
            if (generatedState.validTransition(chosenDisc, chosenRod)){
                generatedState.setStateRepresentation(chosenDisc, chosenRod);
                generatedFitness = calculateFitness(generatedState.getStateRepresentation());
                currentFitness = calculateFitness(currentState.getStateRepresentation());
                if (generatedFitness<currentFitness) {
                    currentState.move(chosenDisc, chosenRod);
                }
                else {
                    currentState.copySolutionList(generatedState.getStateRepresentation());
                }
            }
        }
    }

    public int calculateFitness(List<Integer> solution) {
        int fitness = 0;
        for (int i = 0; i < numberOfDiscs; i++) {
            fitness+= numberOfRods - solution.get(i);
        }
        return fitness;
    }
}
