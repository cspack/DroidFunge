package us.exya.droidfunge.befunge;

import android.graphics.Point;

/**
 * Created by zearen on 09/07/13.
 */
public interface BefungeListener {
    void onMove(Point oldLoc);
    void onPush(BefungeNode pushed);
    void onPop(BefungeNode popped);
    void onModify(Point loc, BefungeNode oldNode);
    void print(BefungeNode node);
    BefungeNode input(Class<? extends BefungeNode> requestType);
}
