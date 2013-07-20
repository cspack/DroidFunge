package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 14/07/13.
 */
public class ConstantNode<T, B extends Befunge> extends BefungeNode<B> {
    private T val;
    private BefungeDraw draw;
    @Override
    public BefungeDraw getDraw() {
        return draw;
    }

    @Override
    public void eval(B befunge) {
        befunge.getStack().push(this);
        befunge.onStack();
    }
}
