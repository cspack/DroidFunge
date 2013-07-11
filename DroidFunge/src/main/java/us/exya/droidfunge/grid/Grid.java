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
    Class<? extends T> tClass;

    // We keep track of the local position and size for optimization purposes
    // [curPos] is in absolute coordinates and always in curLevel;
    protected Point curPos;
    protected Level curSector;
    protected Rect  curSize;

    private class Level {
        public int level;
        public T[][] items = null;
        public Level[][] children = null;
        public Level parent = null;
        public Point relLoc;

        public Level() {
            this(new Point(sectorSize.x / 2, sectorSize.y / 2), null);
        }

        // Upwards constructor
        public Level(Level child) {
            relLoc = new Point(sectorSize.x / 2, sectorSize.y / 2);
            children = (Level[][]) Array.newInstance(this.getClass(),
                    sectorSize.x, sectorSize.y);
            children[child.relLoc.x][child.relLoc.y] = child;
            child.parent = this;
            this.level = child.level + 1;
        }

        // Downwards constructor
        public Level(Point relLoc, Level level) {
            this.relLoc = relLoc;
            parent = level;
            // If this is level 1 or this is the initial level, it's a sector
            // So fill it with actual things
            if (parent == null || parent.level == 2) {
                items = (T[][]) Array.newInstance(tClass,
                        sectorSize.x, sectorSize.y);
                this.level = 1;
            }
            else {
                children = (Level[][]) Array.newInstance(this.getClass(),
                        sectorSize.x, sectorSize.y);
                this.level = parent.level - 1;
            }
        }
    }

    public void setFactory(Factory<T> factory) {
        this.factory = factory;
    }

    public Rect getSize() {
        return new Rect(curSize);
    }

    private Point getSectorSize() {
        return new Point(sectorSize);
    }

    public Grid(Class<? extends T> tClass) {
        this(tClass, new Point(16, 16));
    }

    public Grid(Class<? extends T> tClass, Point sectorSize) {
        this.sectorSize = sectorSize;
        this.tClass = tClass;

        curPos = new Point(0,0);
        curSector = new Level();
        curSize = new Rect(0,0,0,0);
    }

    private Point getSector(Point pnt, Point size) {
        return new Point(
                pnt.x / size.x,
                pnt.y / size.y);
    }

    // This moves the [curPos] to [dest] creating new
    // Sectors and levels as necessary.
    private void moveTo(Point dest) {
        Point relDest = new Point(
                dest.x - curPos.x,
                dest.y - curPos.y);
        curSector = zoomOutIn(curSector, relDest);
        curPos = dest;
    }

    private Level zoomOutIn(Level curLevel, Point relDest) {
        //if (relDest.x ) ;
        return null;
    }

    public T getAt(Point at) {
        moveTo(at);
        return curSector.items
                [curPos.x % sectorSize.x]
                [curPos.y % sectorSize.y];
    }

    public void setAt(Point at, T item) {
        moveTo(at);
        curSector.items
                [curPos.x % sectorSize.x]
                [curPos.y % sectorSize.y]
                = item;
    }

    public T[][] getSlice(Rect slice) {
        return null;
    }

    public T[][] getAll() {
        return getSlice(curSize);
    }
}