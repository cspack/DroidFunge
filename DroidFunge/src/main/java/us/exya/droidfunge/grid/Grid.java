package us.exya.droidfunge.grid;

import android.graphics.Point;
import android.graphics.Rect;

import java.lang.reflect.Array;

/**
 * Created by zearen on 09/07/13.
 */
public class Grid<T> {
    protected Point sectorSize;
    Class<? extends T> tClass;

    // We keep track of the local position and size for optimization purposes
    // [curPos] is in absolute coordinates and always in curLevel;
    protected Point curPos;
    protected Level curSector;
    protected Rect  curSize;


    private class Level {
        public int level;
        private T[][] items = null;
        public Level[][] children = null;
        public Level parent = null;
        public Point relPos;
        private boolean modified = false;

        public Level() {
            relPos = new Point(sectorSize.x / 2, sectorSize.y / 2);
            items = (T[][]) Array.newInstance(tClass,
                    sectorSize.x, sectorSize.y);
            level = 0;
        }

        // Upwards constructor
        public Level(Level child) {
            relPos = new Point(sectorSize.x / 2, sectorSize.y / 2);
            children = (Level[][]) Array.newInstance(this.getClass(),
                    sectorSize.x, sectorSize.y);
            // Only keep track of a child if it has data in it
            if (child.modified) {
                children[child.relPos.x][child.relPos.y] = child;
                child.parent = this;
            }
            this.level = child.level + 1;
        }

        // Downwards constructor
        public Level(Point relLoc, Level level) {
            this.relPos = relLoc;
            parent = level;
            this.level = parent.level - 1;
            // If this is level 0, it's a sector so fill it with actual things
            if (parent.level == 1) {
                items = (T[][]) Array.newInstance(tClass,
                        sectorSize.x, sectorSize.y);
            }
            else {
                children = (Level[][]) Array.newInstance(this.getClass(),
                        sectorSize.x, sectorSize.y);
            }
        }

        public T getAt(Point pnt) {
            return items
                    [rem(pnt.x, sectorSize.x)]
                    [rem(pnt.y, sectorSize.y)];
        }

        public void setAt(Point pnt, T item) {
            items
                    [rem(pnt.x, sectorSize.x)]
                    [rem(pnt.y, sectorSize.y)]
                    = item;
            modified = item != null;
        }
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

    // Assures modulo is positive
    private static int rem(int n, int m) {
        int ret = n % m;
        if (ret < 0) ret += m;
        return ret;
    }

    private Point getSector(Point pnt) {
        return getSector(pnt, sectorSize);
    }

    private Point getSector(Point pnt, Point size) {
        return new Point(
                Math.abs(pnt.x / size.x),
                Math.abs(pnt.y / size.y));
    }

    // This moves the [curPos] to [dest] creating new
    // Sectors and levels as necessary.
    private void moveTo(Point dest) {
        if (!getSector(curPos).equals(getSector(dest))) {
            Point relDest = new Point(
                    dest.x - curPos.x,
                    dest.y - curPos.y);
            Level curLevel = curSector;
            Point relPos = new Point(
                    rem((curPos.x - sectorSize.x / 2), sectorSize.x),
                    rem((curPos.y - sectorSize.y / 2), sectorSize.y));
            Point size = new Point(1,1);
            // The direction we're moving
            boolean xFound = relDest.x == 0;
            boolean yFound = relDest.y == 0;
            while (!(xFound && yFound)) {
                size.x *= sectorSize.x;
                size.y *= sectorSize.y;
                if (xFound) {
                    relDest.x += size.x * relPos.x;
                }
                else {
                    if (relDest.x > 0) {

                        if (relDest.x <= 0) {
                            relDest.x = size.x * sectorSize.x + relDest.x - 1;
                            xFound = true;
                        }
                    }
                }
                if (curLevel.parent == null) {
                    curLevel.parent = new Level(curLevel);
                }
                curLevel = curLevel.parent;
            }
            while (curLevel.level > 0) {
                int x = relDest.x / size.x;
                int y = relDest.y / size.y;
                relDest.x %= size.x;
                relDest.y %= size.y;
                if (curLevel.children[x][y] == null) {
                    curLevel.children[x][y] = new Level(new Point(x,y), curLevel);
                }
                curLevel = curLevel.children[x][y];
                size.x /= sectorSize.x;
                size.y /= sectorSize.y;
            }
        }
        curPos = dest;
    }

    public T getAt(Point at) {
        moveTo(at);
        return curSector.getAt(curPos);
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