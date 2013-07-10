package us.exya.droidfunge.grid;

import android.graphics.Point;

/**
 * Created by zearen on 09/07/13.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public void offset(Point pnt) {
        switch (this) {
            case NORTH:
                pnt.offset(0,-1);
                break;
            case EAST:
                pnt.offset(1,0);
                break;
            case SOUTH:
                pnt.offset(0,1);
                break;
            case WEST:
                pnt.offset(-1, 0);
                break;
        }
    }
}
