package us.exya.droidfunge.befunge;

import android.graphics.Point;
import android.graphics.Rect;

import us.exya.droidfunge.befunge.nodes.EmptyNode;
import us.exya.droidfunge.grid.Grid;

/**
 * Created by zearen on 12/07/13.
 */
public class BefungeGrid extends Grid<BefungeNode> {
    protected Rect curSize = new Rect(0,0,0,0);

    public BefungeGrid() {
        super(BefungeNode.class);
    }

    public Rect getSize() {
        return new Rect(curSize);
    }

    @Override
    public BefungeNode getAt(Point at) {
        BefungeNode ret = super.getAt(at);
        if (ret == null) {
            ret = EmptyNode.EMPTY_NODE;
        }
        return ret;
    }

    @Override
    public void setAt(Point at, BefungeNode node) {
        if (node instanceof EmptyNode) {
            node = null;
        }
        else {
            // Check to expand size
        }
        super.setAt(at, node);
    }

    public BefungeNode[][] getAll() {
        return getSlice(curSize);
    }
}
