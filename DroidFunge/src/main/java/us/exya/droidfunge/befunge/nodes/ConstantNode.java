package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 14/07/13.
 */
public class ConstantNode<T, B extends Befunge> implements BefungeNode<B> {
    private T val;
    private BefungeDraw draw;

    public ConstantNode(T val) {
        this.val = val;
        draw = new BefungeDraw(val.toString());
    }

    @Override
    public BefungeDraw getDraw() {
        return draw;
    }

    @Override
    public void eval(B befunge) {
        befunge.push(this);
        befunge.onStack("Push");
    }

    @Override
    public boolean isTruthy() {
        return true;
    }
}
