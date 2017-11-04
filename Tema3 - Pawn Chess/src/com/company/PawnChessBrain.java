package com.company;

import com.company.problem.State;

import java.util.ArrayList;
import java.util.List;

public class PawnChessBrain {
    private static final int NUMBER_OF_MOVES_AHEAD = 2;
    public static State chooseNextMove(State currentState) {
        StateTree stateTree = new StateTree(currentState);

        for (int i = 0; i < NUMBER_OF_MOVES_AHEAD; i++) {

        }

        return stateTree.root.state;
    }
}

class StateTree {
    StateNode root;

    public StateTree(State rootData) {
        root = new StateNode();
        root.state = rootData;
        root.children = new ArrayList<>();
    }

    static class StateNode {
        State state;
        StateNode parent;
        List<StateNode> children;
    }
}
