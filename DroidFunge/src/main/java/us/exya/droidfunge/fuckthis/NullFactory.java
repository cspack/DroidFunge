package us.exya.droidfunge.fuckthis;

/**
 * Created by zearen on 10/07/13.
 */
public class NullFactory<T> implements Factory<T> {
    @Override
    public T getNew() {
        return null;
    }
}
