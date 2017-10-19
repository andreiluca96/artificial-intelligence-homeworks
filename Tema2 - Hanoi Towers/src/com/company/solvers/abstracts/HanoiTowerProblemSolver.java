package com.company.solvers.abstracts;

import com.company.problems.HanoiTowerProblem;
import com.company.problems.State;
import com.company.solvers.exceptions.StuckAlgorithmException;

import java.util.List;

/**
 * Created by Luca Andrei on 10/18/2017.
 */
public interface HanoiTowerProblemSolver {
    List<State> solve(HanoiTowerProblem problem) throws StuckAlgorithmException;
}
