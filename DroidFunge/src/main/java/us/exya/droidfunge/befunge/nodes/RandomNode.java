package us.exya.droidfunge.befunge.nodes;

import java.util.Random;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.grid.Direction;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 20/07/13.
 */
public class RandomNode<B extends Befunge> implements BefungeNode<B> {
    public static final BefungeDraw DRAW = new BefungeDraw("?");
    public static final Random generator = new Random();

    @Override
    public BefungeDraw getDraw() {
        return DRAW;
    }

    @Override
    public void eval(B befunge) {
        befunge.setDir(Direction.values()[generator.nextInt() % 4]);
    }

    @Override
    public boolean isTruthy() {
        return true;
    }
}
