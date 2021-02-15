package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    private int first; // index for the next dequeue or peek
    private int last; // index for the next enqueue
    private T[] rb; // array for storing the buffer data

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }


    private int plusOne(int index) {
        return (index + 1) % capacity;
    }

    /**
     * Adds x to the end of the ring buffer.
     * If there is no room, then throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer.
     * If the buffer is empty, then throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        rb[first] = null;
        first = plusOne(first);
        fillCount -= 1;
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow.");
        }
        return rb[first];
    }

    private class ARBIterator implements Iterator<T> {
        private int pos; // tracks start position of buffer (oldest item -> newest)
        private int counter; // tracks # of indexes accessed

        public ARBIterator() {
            pos = first;
        }

        @Override
        public boolean hasNext() {
            return counter < fillCount();
        }

        @Override
        public T next() {
            T item = rb[pos];
            pos = plusOne(pos);
            counter++;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

}
