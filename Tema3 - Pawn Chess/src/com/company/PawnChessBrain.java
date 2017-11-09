package com.company;

import com.company.problem.Pawn;
import com.company.problem.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.exp;

public class PawnChessBrain {

    public static final int MINUS_INFINITY = -99999;
    public static final int INFINITY = 99999;

    public static State chooseNextMove(State currentState) {
        StateTree stateTree = new StateTree(currentState);

        /*
          Generate the computer moves.
         */
        for (State state : currentState.generatePossibleState(true)) {
            StateNode stateNode = new StateNode();
            stateNode.state = state;
            stateNode.parent = stateTree.root;

            stateTree.root.children.add(stateNode);
        }

        /*
         * For every computer move, generate the human moves.
         */
        for (StateNode stateNode : stateTree.root.children) {
            for (State state : stateNode.state.generatePossibleState(false)) {
                StateNode newStateNode = new StateNode();
                newStateNode.state = state;
                newStateNode.parent = stateTree.root;

                stateNode.children.add(newStateNode);
            }
        }

        postOrderTraversalWithMinMax(stateTree.root, 0);

        /*
            Choose the best from the available moves.
         */
        double maximum = MINUS_INFINITY;
        List<State> listOfMaximums = new ArrayList<>();
        State maximumState = null;
        for (StateNode stateNode : stateTree.root.children) {
            if (maximum == stateNode.score) {
                maximum = stateNode.score;
                maximumState = stateNode.state;
                listOfMaximums.add(maximumState);
            }
            if (maximum < stateNode.score ) {
                if(!listOfMaximums.isEmpty()) {
                    listOfMaximums.clear();
                }
                maximum = stateNode.score;
                maximumState = stateNode.state;
                listOfMaximums.add(maximumState);
            }
        }
        Random random = new Random();
        int index = random.nextInt(listOfMaximums.size());
        maximumState = listOfMaximums.get(index);
        return maximumState;
    }

    public static double getStateScore(State state) {

        int blackRemainingMinimum = 8;
        for (Pawn pawn : state.getBlackPawns()) {
            if (blackRemainingMinimum > pawn.getLine()) {
                blackRemainingMinimum = pawn.getLine();
            }
        }

        int whiteRemainingMinimum = 8;
        for (Pawn pawn : state.getWhitePawns()) {
            if (whiteRemainingMinimum > 7 - pawn.getLine()) {
                whiteRemainingMinimum = 7 - pawn.getLine();
            }
        }

        int blackRemainingPawns = state.getBlackPawns().size();
        int whiteRemainingPawns = state.getWhitePawns().size();

        return exp(blackRemainingMinimum - whiteRemainingMinimum) *
                (blackRemainingPawns + state.getDimension() - whiteRemainingPawns);
    }

    private static void postOrderTraversalWithMinMax(StateNode stateNode, int height) {
        for (StateNode child : stateNode.children) {
            postOrderTraversalWithMinMax(child, height + 1);
        }

        if (stateNode.children.size() == 0) {
            stateNode.score = getStateScore(stateNode.state);
            return;
        }

        if (height % 2 == 0) {
            /*
            Choose maximum from children.
             */

            double maximum = MINUS_INFINITY;

            for (StateNode child : stateNode.children) {
                if (maximum < child.score) {
                    maximum = child.score;
                }
            }

            stateNode.score = maximum;

        } else {
            /*
            choose minimum from children.
             */

            double minimum = INFINITY;

            for (StateNode child : stateNode.children) {
                if (minimum > child.score) {
                    minimum = child.score;
                }
            }

            stateNode.score = minimum;
        }
    }
}

class StateTree {
    StateNode root;
    public StateTree(State rootData) {
        root = new StateNode();
        root.state = rootData;
        root.children = new ArrayList<>();
        root.parent = null;
    }
}
class StateNode {
    State state;
    StateNode parent;
    double score;
    List<StateNode> children = new ArrayList<>();
}
