package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 20/07/13.
 */
public class InputNode<B extends Befunge> implements BefungeNode<B> {

    protected Class<? extends BefungeNode> inputRequest;
    protected BefungeDraw draw;

    InputNode(Class<? extends BefungeNode> inputRequest, BefungeDraw draw) {
        this.inputRequest = inputRequest;
        this.draw = draw;
    }

    @Override
    public BefungeDraw getDraw() {
        return draw;
    }

    @Override
    public void eval(B befunge) {
        befunge.push(befunge.input(inputRequest));
        befunge.onStack("Push");
    }

    @Override
    public boolean isTruthy() {
        return true;
    }
}
