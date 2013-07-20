package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.grid.Direction;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 14/07/13.
 */
public class ArrowNode<B extends Befunge> extends BefungeNode<B> {
    private Direction dir;

    public static final BefungeDraw NORTH = new BefungeDraw("^");
    public static final BefungeDraw EAST = new BefungeDraw(">");
    public static final BefungeDraw SOUTH = new BefungeDraw("v");
    public static final BefungeDraw WEST = new BefungeDraw("<");

    public ArrowNode(Direction dir) {
        this.dir = dir;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    @Override
    public BefungeDraw getDraw() {
        switch (getDir()) {
            case NORTH: return NORTH;
            case EAST:  return EAST;
            case SOUTH: return SOUTH;
            case WEST:  return WEST;
        }
        return null; // This will never happen
    }

    @Override
    public void eval(B befunge) {
        befunge.setDir(dir);
    }
}
