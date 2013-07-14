package us.exya.droidfunge.befunge;

import android.graphics.Point;

/**
 * Created by zearen on 14/07/13.
 */
public class BefungeAdapter implements BefungeListener {

    @Override
    public void onMove(Point oldLoc) {}

    @Override
    public void onPush(BefungeNode pushed) {}

    @Override
    public void onPop(BefungeNode popped) {}

    @Override
    public void onModify(Point loc, BefungeNode oldNode) {}

    @Override
    public void print(BefungeNode node) {}

    @Override
    public BefungeNode input(Class<? extends BefungeNode> requestType) {
        return null;
    }
}
