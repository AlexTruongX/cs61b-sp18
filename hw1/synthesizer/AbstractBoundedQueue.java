package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    /* "Getter" Methods */
    @Override
    public int fillCount() {
        return fillCount;
    }
    @Override
    public int capacity() {
        return capacity;
    }

}
