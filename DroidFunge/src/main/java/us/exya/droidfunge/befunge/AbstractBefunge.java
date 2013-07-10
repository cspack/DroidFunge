package us.exya.droidfunge.befunge;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by zearen on 09/07/13.
 */
public abstract class AbstractBefunge {
    private Deque<BefungeNode> stack = new LinkedList<BefungeNode>();

    public Deque<BefungeNode> getStack() {
        return stack;
    }
}
