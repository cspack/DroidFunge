package us.exya.droidfunge.grid;

import android.graphics.Point;
import android.graphics.Rect;

import java.lang.reflect.Array;

/**
 * Created by zearen on 09/07/13.
 */
public class Grid<T> {
    protected Point sectorSize;
    protected  Class<? extends T> tClass;

    // We keep track of the local position and size for optimization purposes
    // [curPos] is in absolute coordinates and always in curLevel;
    // This is protected for a reason.  Exposing it will incite a gnome riot.
    protected Point curPos;
    protected Level curSector;


    private class Level {
        // This is technically unnecessary, but convenient for debugging
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
            if (child.isModified()) {
                children[child.relPos.x][child.relPos.y] = child;
                child.parent = this;
                modified = true;
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

        public boolean isModified() {
            return modified;
        }

        public T getAt(Point pnt) {
            return items
                    [rem(pnt.x + sectorSize.x / 2, sectorSize.x)]
                    [rem(pnt.y + sectorSize.y / 2, sectorSize.y)];
        }

        public void setAt(Point pnt, T item) {
            items
                    [rem(pnt.x + sectorSize.x / 2, sectorSize.x)]
                    [rem(pnt.y + sectorSize.y / 2, sectorSize.y)]
                    = item;
            // If this item was modified, recursively
            // set all higher levels as modified.
            if (!isModified() && item != null) {
                Level ancestor = parent;
                while (ancestor != null && !ancestor.isModified()) {
                    ancestor.modified = true;
                    ancestor = ancestor.parent;
                }
            }
        }
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
                Math.abs((pnt.x  + sectorSize.x) / size.x),
                Math.abs((pnt.y + sectorSize.y) / size.y));
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
                    rem((curPos.x + sectorSize.x / 2), sectorSize.x),
                    rem((curPos.y + sectorSize.y / 2), sectorSize.y));
            Point size = new Point(1,1);
            // The direction we're moving
            boolean xFound = relDest.x == 0;
            boolean yFound = relDest.y == 0;
            while (!(xFound && yFound)) {
                if (xFound) {
                    relDest.x += relPos.x * size.x;
                }
                else {
                    if (relDest.x > 0) {
                        relDest.x -= (sectorSize.x - 1 - relPos.x) * size.x;
                        if (relDest.x <= 0) {
                            relDest.x = size.x * sectorSize.x + relDest.x - 1;
                            xFound = true;
                        }
                    }
                    else {
                        relDest.x += relPos.x * size.x;
                        if (relDest.x >= 0) {
                            xFound = true;
                        }
                    }
                }
                if (yFound) {
                    relDest.y += relPos.y * size.y;
                }
                else {
                    if (relDest.y > 0) {
                        relDest.y -= (sectorSize.y - 1 - relPos.y) * size.y;
                        if (relDest.y <= 0) {
                            relDest.y += size.y * sectorSize.y - 1;
                            yFound = true;
                        }
                    }
                    else {
                        relDest.y += relPos.y * size.y;
                        if (relDest.y >= 0) {
                            yFound = true;
                        }
                    }
                }
                size.x *= sectorSize.x;
                size.y *= sectorSize.y;
                if (curLevel.parent == null) {
                    curLevel.parent = new Level(curLevel);
                }
                relPos = curLevel.relPos;
                boolean wasModified = curLevel.isModified();
                curLevel = curLevel.parent;
                // If the previous level wasn't modified, forget it.
                if (!wasModified) {
                    curLevel.children[relPos.x][relPos.y] = null;
                }
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

    // This gets an array of items in a given rectangle
    // It's slightly more effecient since it doesn't leave
    //+ a sector until it has to.
    // Leaves curPos at bottom right corner
    public T[][] getSlice(Rect slice) {
        T[][] ret = (T[][]) Array.newInstance(tClass,
                slice.width(), slice.height());
        Point retPos = new Point(0,0);
        Rect curSlice = new Rect(slice.left, slice.top,
            Math.min(slice.left * sectorSize.x + sectorSize.x / 2
                    + sectorSize.x % 2 - 1, slice.right),
            Math.min(slice.top * sectorSize.y + sectorSize.y / 2
                    + sectorSize.y % 2 - 1, slice.bottom));
        while (true) {
            while (true) {
                Point pos = new Point(curSlice.left, curSlice.top);
                moveTo(pos);

                for (; pos.y <= curSlice.bottom; pos.y += 1)
                for (; pos.x <= curSlice.right; pos.x += 1) {
                    // This does some expensive math, can optimize if needed
                    ret[retPos.x][retPos.y] = curSector.getAt(pos);
                    retPos.x += 1;
                    retPos.y += 1;
                }

                if (curSlice.right >= slice.right) {
                    break;
                }
                else {
                    curSlice.left = slice.right + 1;
                    curSlice.right = Math.min(
                            curSlice.right + sectorSize.x, slice.right);
                }
            }
            if (curSlice.bottom >= slice.bottom) {
                break;
            }
            else {
                curSlice.top = curSlice.bottom + 1;
                curSlice.bottom = Math.min
                        (curSlice.bottom + sectorSize.y, slice.bottom);
            }
        }
        return ret;
    }

    // Overrideable for decendent classes that wish to
    //+ produce a default value
    protected T ifNull() {
        return null;
    }
}