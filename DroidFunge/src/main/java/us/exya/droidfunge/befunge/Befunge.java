package us.exya.droidfunge.befunge;

import android.graphics.Point;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import us.exya.droidfunge.befunge.nodes.EmptyNode;
import us.exya.droidfunge.grid.Direction;

/**
 * Created by zearen on 09/07/13.
 */
public class Befunge {
    private Deque<BefungeNode> stack = new LinkedList<BefungeNode>();
    private Point delta;
    private Point loc;
    private BefungeGrid grid = new BefungeGrid();
    private List<BefungeListener> listeners = new LinkedList<BefungeListener>();

    // Event functions.  Note that though these are public, they should really
    //+ be "friend <? extends BefungeNode>"
    public void onMove(Point oldLoc) {
        for (BefungeListener listener : listeners) {
            listener.onMove(oldLoc);
        }
    }

    public void onStack(String op) {
        for (BefungeListener listener : listeners) {
            listener.onStack(stack, op);
        }
    }

    public void onModify(BefungeNode oldNode) {
        for (BefungeListener listener : listeners) {
            listener.onModify(loc, oldNode);
        }
    }

    public void print(BefungeNode node) {
        for (BefungeListener listener : listeners) {
            listener.print(node);
        }
    }

    public BefungeNode input(Class<? extends BefungeNode> requestType) {
        for (BefungeListener listener : listeners) {
            BefungeNode result = listener.input(requestType);
            if (result != null) return result;
        }
        return EmptyNode.EMPTY_NODE;
    }

    public void onEnd() {
        for (BefungeListener listener : listeners) {
            listener.onEnd(loc);
        }
    }

    public void onException(BefungeException ex) {
        for (BefungeListener listener : listeners) {
            listener.onException(ex);
        }
    }

    public void addListener(BefungeListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        listeners.add(listener);
    }

    public void removeListener(BefungeListener listener) {
        listeners.remove(listener);
    }

    public Deque<BefungeNode> getStack() {
        return stack;
    }

    public BefungeNode pop() {
        if (stack.isEmpty()) {
            return EmptyNode.EMPTY_NODE;
        }
        else {
            return stack.pop();
        }
    }

    public void push(BefungeNode node) {
        stack.push(node);
    }

    public Point getDelta() {
        return delta;
    }

    public void setDelta(Point delta) {
        this.delta = delta;
    }

    public void setDir(Direction dir) {
        delta.set(0,0);
        dir.offset(delta);
    }

    public Point getLoc() {
        return loc;
    }

    public void setLoc(Point loc) {
        Point oldLoc = this.loc;
        this.loc = loc;
        onMove(oldLoc);
    }

    public void tiptoe() {
        Point oldLoc = new Point(loc);
        loc.x += delta.x;
        loc.y += delta.y;
        onMove(oldLoc);
    }

    public void step() {
        this.tiptoe();
        grid.getAt(loc).eval(this);
    }
}
