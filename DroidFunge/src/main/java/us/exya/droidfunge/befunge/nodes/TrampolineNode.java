package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 14/07/13.
 */
public class TrampolineNode extends BefungeNode {
    public static final BefungeDraw DRAW = new BefungeDraw("#");

    @Override
    public BefungeDraw getDraw() {
        return DRAW;
    }

    @Override
    public void eval(Befunge befunge) {
        befunge.tiptoe();
    }
}
