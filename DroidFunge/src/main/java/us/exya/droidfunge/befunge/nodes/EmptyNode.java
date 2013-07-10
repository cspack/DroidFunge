package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 10/07/13.
 */
public class EmptyNode extends BefungeNode {
    private static final BefungeDraw DRAW = new BefungeDraw(" ");

    @Override
    public BefungeDraw getDraw() {
        return DRAW;
    }

    @Override
    public void eval(Befunge befunge) {}
}
