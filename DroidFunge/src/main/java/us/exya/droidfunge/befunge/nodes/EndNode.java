package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 20/07/13.
 */
public class EndNode<B extends Befunge> implements BefungeNode<B> {
    public static final BefungeDraw DRAW = new BefungeDraw("@");

    @Override
    public BefungeDraw getDraw() {
        return DRAW;
    }

    @Override
    public void eval(B befunge) {
        befunge.onEnd();
    }

    @Override
    public boolean isTruthy() {
        return true;
    }
}
