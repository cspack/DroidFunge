package us.exya.droidfunge.befunge.nodes;

import us.exya.droidfunge.befunge.Befunge;
import us.exya.droidfunge.befunge.BefungeException;
import us.exya.droidfunge.befunge.BefungeNode;
import us.exya.droidfunge.befunge.exceptions.BefungeTypeException;
import us.exya.droidfunge.ui.BefungeDraw;

/**
 * Created by zearen on 20/07/13.
 */
public abstract class OpNode<B extends Befunge, N extends BefungeNode<B>>
        implements BefungeNode<B> {

    @Override
    public final void eval(B befunge) {
        N topNode;
        N nextNode;
        BefungeNode node = null;
        try {
            node = befunge.pop();
            topNode = (N) node;
            node = befunge.pop();
            nextNode = (N) node;
        }
        catch (ClassCastException e) {
            befunge.onStack("Op");
            befunge.onException(new BefungeTypeException(getNodeClass(), node.getClass()));
            return;
        }
        befunge.onStack("O");
        befunge.push(op(topNode, nextNode));
    }

    public abstract Class<N> getNodeClass();

    public abstract BefungeNode op(N topNode, N nextNode);
}
