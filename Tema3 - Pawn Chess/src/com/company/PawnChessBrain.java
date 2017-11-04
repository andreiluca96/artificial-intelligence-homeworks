package com.company;

import com.company.problem.State;

import java.util.ArrayList;
import java.util.List;

public class PawnChessBrain {
    public static State chooseNextMove(State currentState) {
        StateTree stateTree = new StateTree(currentState);

        /**
         * Generate the computer moves.
         */
        for (State state : currentState.generatePossibleState(false)) {
            StateNode stateNode = new StateNode();
            stateNode.state = state;
            stateNode.parent = stateTree.root;

            stateTree.root.children.add(stateNode);
        }

        /**
         * For every computer move, generate the human moves.
         */
        for (StateNode stateNode : stateTree.root.children) {
            for (State state : stateNode.state.generatePossibleState(true)) {
                StateNode newStateNode = new StateNode();
                newStateNode.state = state;
                newStateNode.parent = stateTree.root;

                stateNode.children.add(stateNode);
            }
        }

        List<Integer> humanMinumum = new ArrayList<>();


        return stateTree.root.state;
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
    List<StateNode> children = new ArrayList<>();
}
