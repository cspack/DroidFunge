package us.exya.droidfunge.befunge.exceptions;

import us.exya.droidfunge.befunge.BefungeException;
import us.exya.droidfunge.befunge.BefungeNode;

/**
 * Created by zearen on 20/07/13.
 */
public class BefungeTypeException extends BefungeException {
    private Class<? extends BefungeNode> expected;
    private Class<? extends BefungeNode> recieved;

    public BefungeTypeException(Class<? extends BefungeNode> expected,
                                Class<? extends BefungeNode> recieved) {
        this.expected = expected;
        this.recieved = recieved;
    }

    public Class<? extends BefungeNode> getExpected() {
        return expected;
    }

    public Class<? extends BefungeNode> getRecieved() {
        return recieved;
    }
}
