package com.company.solvers;

import com.company.problems.HanoiTowerProblem;
import com.company.problems.State;

import java.util.List;

/**
 * Created by Luca Andrei on 10/18/2017.
 */
public interface HanoiTowerProblemSolver {
    List<State> solve(HanoiTowerProblem problem);
}
