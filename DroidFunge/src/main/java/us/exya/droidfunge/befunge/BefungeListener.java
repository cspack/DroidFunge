package us.exya.droidfunge.befunge;

/**
 * Created by zearen on 09/07/13.
 */
public interface BefungeListener {
    void onMove(BefungeNode bNode);
    void onPushStack();
    void onPopStack();
    void print(BefungeNode bNode);
    BefungeNode input(Class<? extends BefungeNode> requestType);
}
