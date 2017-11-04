package com.company;

import com.company.problem.Pawn;
import com.company.problem.State;

import java.util.Scanner;

public class Main {

    public static final int TABLE_SIZE = 8;

    public static void main(String[] args) {
        State state = new State(TABLE_SIZE);
        state.printState();

        while(!state.isFinalState()) {

            /*
            Human move.
             */
            Scanner scan = new Scanner(System.in);

            System.out.println("Insert the pawn you want to move: ");
            Pawn currentPawn = new Pawn(scan.nextInt() - 1, scan.nextInt() - 1);

            System.out.println("Insert the position to which you want to move: ");
            Pawn movedPawn = new Pawn(scan.nextInt() - 1, scan.nextInt() - 1);

//        Pawn currentPawn = new Pawn(7 - 1, 1 - 1);
//        Pawn movedPawn = new Pawn(5 - 1, 1 - 1);

        state.moveBlack(currentPawn, movedPawn);

        System.out.println("Your move result:");
        state.printState();

            /*
            Computer move.
             */

        System.out.println("Computer move:");
        state = PawnChessBrain.chooseNextMove(state);

        state.printState();

        }

        if (state.getWinner() == 1) {
            System.out.println("The computer won!!");
        }
        if (state.getWinner() == 0) {
            System.out.println("Equal game!");
        }
        if (state.getWinner() == 0) {
            System.out.println("You won! Congrats!");
        }
    }


}
