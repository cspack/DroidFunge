package us.exya.droidfunge.grid;

import android.graphics.Point;
import android.graphics.Rect;

import java.lang.reflect.Array;
import java.net.NoRouteToHostException;
import java.util.Arrays;

import us.exya.droidfunge.fuckthis.Factory;
import us.exya.droidfunge.fuckthis.NullFactory;

import static us.exya.droidfunge.grid.Direction.*;

/**
 * Created by zearen on 09/07/13.
 */
public class Grid<T> {
    protected Factory<T> factory = new NullFactory<T>();

    protected Point sectorSize;

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    Grid() {
        this(new Point(64, 64));
    }

    Grid(Point sectorSize) {
        this.sectorSize = sectorSize;
    }

    public T getAt(Point at) {
        return null;
    }

    public void setAt(Point at, T item){

    }

    public T[][] getSlice(Rect slice) {
        return null;
    }
}