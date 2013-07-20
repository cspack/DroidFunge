package us.exya.droidfunge.befunge.nodes;

import java.util.Deque;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 20/07/13.
 */
public class SwapNode<B extends Befunge> implements BefungeNode<B> {
    public static final BefungeDraw DRAW = new BefungeDraw("\\");

    @Override
    public BefungeDraw getDraw() {
        return DRAW;
    }

    @Override
    public void eval(B befunge) {
        BefungeNode a = befunge.pop();
        BefungeNode b = befunge.pop();
        befunge.push(a);
        befunge.push(b);
        befunge.onStack("Swap");
    }

    @Override
    public boolean isTruthy() {
        return true;
    }
}
