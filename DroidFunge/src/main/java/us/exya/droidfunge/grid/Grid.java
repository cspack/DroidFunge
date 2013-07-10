package us.exya.droidfunge.grid;

import java.lang.reflect.Array;
import java.net.NoRouteToHostException;
import java.util.Arrays;

import us.exya.droidfunge.fuckthis.Factory;

import static us.exya.droidfunge.grid.Direction.*;

/**
 * Created by zearen on 09/07/13.
 */
public class Grid<T> {
    private Factory<T> factoryT;

    public class GridNode {
        private T contents = null;

        private GridNode[] neighbors;
        public GridNode() {
            neighbors = (GridNode[]) Array.newInstance(GridNode.class, 4);
            if (factoryT != null) {
                contents = factoryT.getNew();
            }
        }

        public T getContents() {
            return contents;
        }

        public void setContents(T newContents) {
            contents = newContents;
        }

        public GridNode getNeighbor(Direction dir) {
            int ord = dir.ordinal();
            int left = (ord - 1) % 4;
            int right = (ord + 1) % 4;
            if (neighbors[ord] == null) {
                neighbors[ord] = new GridNode();

                if (neighbors[right] != null) {
                    GridNode rightNode = neighbors[right].neighbors[ord];
                    if (rightNode != null) {
                        rightNode.neighbors[left] = neighbors[ord];
                        neighbors[ord].neighbors[right] = rightNode;
                    }
                }

                if (neighbors[left] != null) {
                    GridNode leftNode = neighbors[left].neighbors[ord];
                    if (leftNode != null) {
                        leftNode.neighbors[right] = neighbors[ord];
                        neighbors[ord].neighbors[left] = leftNode;
                    }
                }
            }
            return neighbors[ord];
        }
    }

    private GridNode origin;

    public GridNode getOrigin() {
        return origin;
    }

    public void setFactory(Factory<T> newFactory) {
        factoryT = newFactory;
    }
}
