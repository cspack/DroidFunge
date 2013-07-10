package us.exya.droidfunge.befunge;

import android.graphics.Point;

/**
 * Created by zearen on 09/07/13.
 */
public interface BefungeListener {
    void onMove(Point newLoc);
    void onPushStack();
    void onPopStack();
    void onModifyNode(Point loc, BefungeNode oldNode);
    void print(BefungeNode bNode);
    BefungeNode input(Class<? extends BefungeNode> requestType);
}
