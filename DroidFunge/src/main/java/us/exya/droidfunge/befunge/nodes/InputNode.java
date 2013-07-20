package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 20/07/13.
 *
 * It makes far more sense to just directly extend BefungeNode
 * for input classes, but this provides an example
 */
public class InputNode<B extends Befunge> extends BefungeNode<B> {
    protected Class<? extends BefungeNode> inputClass;
    protected BefungeDraw draw;

    public InputNode(Class<? extends BefungeNode>inputClass, BefungeDraw draw) {
        this.inputClass = inputClass;
        this.draw = draw;
    }

    public BefungeDraw getDraw() {
        return draw;
    }

    public void eval(B befunge) {
        befunge.getStack().push(befunge.input(inputClass));
        befunge.onStack();
    }
}
