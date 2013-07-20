package us.exya.droidfunge.befunge;

import android.graphics.Point;

import java.util.Deque;

/**
 * Created by zearen on 09/07/13.
 */
public interface BefungeListener<B extends Befunge> {
    void onMove(Point oldLoc);
    void onStack(Deque<BefungeNode<B>> stack);
    void onModify(Point loc, BefungeNode<B> oldNode);

    void print(BefungeNode<B> node);
    BefungeNode<B> input(Class<? extends BefungeNode<B>> requestType);
    void onEnd(Point loc);
}
