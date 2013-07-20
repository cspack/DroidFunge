package us.exya.droidfunge.befunge;

import android.graphics.Point;

import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 09/07/13.
 */
public interface BefungeNode<B extends Befunge> {
    public BefungeDraw getDraw();

    public void eval(B befunge);

    public boolean isTruthy();
}
