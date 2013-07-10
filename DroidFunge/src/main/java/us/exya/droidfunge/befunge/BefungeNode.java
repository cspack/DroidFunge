package us.exya.droidfunge.befunge;

import android.graphics.Point;

import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 09/07/13.
 */
public abstract class BefungeNode {
    public abstract BefungeDraw getDraw();

    public abstract void eval(Befunge befunge);

    public void run(Befunge befunge) {
        eval(befunge);
        // You have to move afterwards
        befunge.tiptoe();
    }
}
