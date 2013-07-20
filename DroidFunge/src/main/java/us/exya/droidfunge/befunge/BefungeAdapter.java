package us.exya.droidfunge.befunge;

import android.graphics.Point;

import java.util.Deque;

/**
 * Created by zearen on 14/07/13.
 */
public class BefungeAdapter<B extends Befunge> implements BefungeListener<B> {

    @Override
    public void onMove(Point oldLoc) {}

    @Override
    public void onStack(Deque<BefungeNode<B>> stack) {}

    @Override
    public void onModify(Point loc, BefungeNode<B> oldNode) {}

    @Override
    public void print(BefungeNode<B> node) {}

    @Override
    public BefungeNode<B> input(Class<? extends BefungeNode<B>> requestType) {
        return null;
    }

    @Override
    public void onEnd(Point loc) {}
}
