package us.exya.droidfunge.grid;

import android.graphics.Point;

/**
 * Created by zearen on 09/07/13.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public void offset(Point pnt, int step) {
        switch (this) {
            case NORTH:
                pnt.offset(0,-1*step);
                break;
            case EAST:
                pnt.offset(1*step,0);
                break;
            case SOUTH:
                pnt.offset(0,1*step);
                break;
            case WEST:
                pnt.offset(-1*step, 0);
                break;
        }
    }

    public void offset(Point pnt) {
        this.offset(pnt, 1);
    }
}
