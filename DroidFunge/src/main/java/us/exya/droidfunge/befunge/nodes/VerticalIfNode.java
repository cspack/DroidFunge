package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.grid.Direction;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 20/07/13.
 */
public class VerticalIfNode<B extends Befunge> implements BefungeNode<B> {
    public static final BefungeDraw DRAW = new BefungeDraw("|");

    @Override
    public BefungeDraw getDraw() {
        return DRAW;
    }

    @Override
    public void eval(B befunge) {
        BefungeNode node = befunge.pop();
        befunge.onStack("Pop");
        if (node.isTruthy()) {
            befunge.setDir(Direction.NORTH);
        }
        else {
            befunge.setDir(Direction.SOUTH);
        }
    }

    @Override
    public boolean isTruthy() {
        return true;
    }
}
