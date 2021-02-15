package synthesizer;

import java.util.Iterator;
/**
 * 1) Items can only be enqueued at the back of the queue
 * 2) Can only be dequeued from the front of the queue
 * 3) BoundedDeque has a fixed capacity
 * 4) nothing is allowed to enqueue if the queue is full.
 */

public interface BoundedQueue<T> extends Iterable<T>{

    int capacity();     // return size of the buffer
    int fillCount();    // return number of items currently in the buffer
    void enqueue(T x);  // add item x to the end
    T dequeue();        // delete and return item from the front
    T peek();           // return (but do not delete) item from the front

    // Check if the queue is empty
    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    // Check if buffer is full, items in queue are equal to capacity
    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }

    @Override
    Iterator<T> iterator();
}
