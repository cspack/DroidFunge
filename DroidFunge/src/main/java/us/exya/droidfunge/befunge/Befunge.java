package us.exya.droidfunge.befunge;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import us.exya.droidfunge.grid.Direction;
import us.exya.droidfunge.grid.Grid;

/**
 * Created by zearen on 09/07/13.
 */
public class Befunge {
    private Deque<BefungeNode> stack = new LinkedList<BefungeNode>();
    private Direction dir;
    private Point loc;
    private BefungeGrid grid = new BefungeGrid();
    private List<BefungeListener> listeners = new LinkedList<BefungeListener>();

    // Event functions.  Note that though these are public, they should really
    //+ be "friend <? extends BefungeNode>"
    public void onMove(Point newLoc) {
        for (BefungeListener listener : listeners) {
            listener.onMove(newLoc);
        }
    }

    public void onPush(BefungeNode pushed) {
        for (BefungeListener listener : listeners) {
            listener.onPush(pushed);
        }
    }

    public void onPop(BefungeNode popped) {
        for (BefungeListener listener : listeners) {
            listener.onPop(popped);
        }
    }

    public void onModify(Point loc, BefungeNode oldNode) {
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
        return null;
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

    public void push(BefungeNode node) {
        stack.push(node);
        onPush(node);
    }

    public BefungeNode pop() {
        BefungeNode ret = stack.pop();
        onPop(ret);
        return ret;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
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
        dir.offset(loc);
        onMove(oldLoc);
    }

    public void step() {
        grid.getAt(loc).run(this);
    }
}
