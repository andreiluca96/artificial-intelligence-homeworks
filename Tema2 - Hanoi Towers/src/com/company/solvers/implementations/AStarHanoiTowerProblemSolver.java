package com.company.solvers.implementations;

import com.company.problems.HanoiTowerProblem;
import com.company.problems.State;
import com.company.solvers.abstracts.HanoiTowerProblemSolver;
import com.company.solvers.exceptions.StuckAlgorithmException;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luca Andrei on 10/23/2017.
 */
public class AStarHanoiTowerProblemSolver implements HanoiTowerProblemSolver {
    private List<State> openList = new ArrayList<>();
    private List<State> closedList = new ArrayList<>();
    private Map<State, Integer> stateDepthMapping = new HashMap<>();
    private Map<State, State> parentState = new HashMap<>();

    @Override
    public List<State> solve(HanoiTowerProblem problem) throws StuckAlgorithmException {
        State currentState = new State(problem.getNumberOfDiscs(), problem.getNumberOfRods());

        stateDepthMapping.put(new State(currentState), 0);
        openList.add(new State(currentState));
        parentState.put(new State(currentState), null);

        while (!currentState.isFinal()) {
            int minFitness = 999999;
            State minState = new State(problem.getNumberOfDiscs(), problem.getNumberOfRods());
            for (State state : openList) {
                if (fitnessFunction(minState, problem) < minFitness) {
                    minState = state;
                    minFitness = fitnessFunction(minState, problem);
                }
            }

            openList.remove(minState);
            closedList.add(new State(minState));
            currentState = minState;

            for (int i = 0; i < problem.getNumberOfDiscs(); i++) {
                for (int j = 0; j < problem.getNumberOfRods(); j++) {
                    State fakeState = new State(currentState);
                    if (fakeState.move(i, j) && !closedList.contains(fakeState) && !openList.contains(fakeState)) {
                        openList.add(new State(fakeState));
                        stateDepthMapping.put(new State(fakeState), stateDepthMapping.get(currentState) + 1);
                        parentState.put(new State(fakeState), currentState);
                    }
                }
            }
        }

        List<State> gameHistory = new ArrayList<>();
        while (!currentState.isInitial()) {
            gameHistory.add(currentState);
            currentState = parentState.get(currentState);
        }

        return Lists.reverse(gameHistory);
    }

    private int fitnessFunction(State state, HanoiTowerProblem problem) {
        int fitness = stateDepthMapping.get(state);
        for (int i = 0; i < state.getStateRepresentation().size(); i++) {
            if (state.getStateRepresentation().get(i) < problem.getNumberOfRods() - 1) {
                fitness ++;
            } else {
                for (int j = 0; j < i; j++) {
                    if (state.getStateRepresentation().get(j) < problem.getNumberOfRods() - 1) {
                        fitness += 2;
                        break;
                    }
                    fitness -= 2;
                }
            }
        }

        return fitness;
    }
}
