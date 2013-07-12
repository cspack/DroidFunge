package us.exya.droidfunge.befunge;

import android.graphics.Point;

import java.util.Deque;
import java.util.LinkedList;
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
    private Grid<BefungeNode> grid = new Grid<BefungeNode>(BefungeNode.class);

    public Deque<BefungeNode> getStack() {
        return stack;
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
        this.loc = loc;
    }

    public void addListener(BefungeListener listener) {

    }

    public void removeListener(BefungeListener listener) {

    }

    public void tiptoe() {
        dir.offset(loc);
        // onMove(loc);
    }

    public void step() {
        grid.getAt(loc).run(this);
    }
}
