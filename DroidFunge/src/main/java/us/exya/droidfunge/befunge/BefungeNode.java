package us.exya.droidfunge.befunge;

import android.graphics.Point;

import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 09/07/13.
 */
public abstract class BefungeNode<B extends Befunge> {
    public abstract BefungeDraw getDraw();

    public abstract void eval(B befunge);

    public void run(B befunge) {
        eval(befunge);
        // You have to move afterwards
        // (This dissuades infinite loops)
        befunge.tiptoe();
    }

    public boolean isTruthy() {
        return true;
    }
}
