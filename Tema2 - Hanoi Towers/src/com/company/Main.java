package com.company;

import com.company.problems.HanoiTowerProblem;
import com.company.solvers.HanoiTowerProblemSolver;
import com.company.solvers.TotalRandomHanoiTowerProblemSolver;

public class Main {
    public static void main(String[] args) {
	    HanoiTowerProblem problem = new HanoiTowerProblem(2, 3);
        HanoiTowerProblemSolver problemSolver = new TotalRandomHanoiTowerProblemSolver();

        System.out.println("Total random:");
        problemSolver.solve(problem).forEach(System.out::println);

    }
}
