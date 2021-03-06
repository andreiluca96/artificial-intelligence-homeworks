package com.company;

import com.company.problems.HanoiTowerProblem;
import com.company.solvers.abstracts.HanoiTowerProblemSolver;
import com.company.solvers.exceptions.StuckAlgorithmException;
import com.company.solvers.implementations.BacktrackingHanoiTowerProblemSolver;
import com.company.solvers.implementations.HillClimbingHanoiTowerProblemSolver;
import com.company.solvers.implementations.SmartRandomHanoiTowerProblemSolver;
import com.company.solvers.implementations.TotalRandomHanoiTowerProblemSolver;

public class Main {
    public static void main(String[] args) {
	    HanoiTowerProblem problem = new HanoiTowerProblem(4, 3);
        HanoiTowerProblemSolver problemSolver = new TotalRandomHanoiTowerProblemSolver();

//        System.out.println("TotalRandom:");
//        try {
//            problemSolver.solve(problem).forEach(System.out::println);
//        } catch (StuckAlgorithmException e) {
//            e.printStackTrace();
//        }

        problemSolver = new HillClimbingHanoiTowerProblemSolver();
        System.out.println("Backtracking:");
        try {
            problemSolver.solve(problem).forEach(System.out::println);
        } catch (StuckAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
