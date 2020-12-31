/**
 * Deque implementation using circular array
 *
 * @author Alex Truong
 */

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst; // Pointer to front
    private int nextLast; // Pointer to last
    private double usageFactor = 0.25;

    /*  Invariants:
     *   1. nextFirst is the INDICE used when adding a new item to front of the list.
     *   2. nextLast is the INDICE used when adding a new item to the end of the list.
     *   3. Size always represents the amount of items in the list.
     * -------------------------------------------------------------------------------
     *   4. plusOne(nextFirst) is the INDICE of the CURRENT FIRST item in the deque.
     *   5. minusOne(nextLast) is the INDICE of the CURRENT LAST item in the deque.
     * -------------------------------------------------------------------------------
     *   6. minusOne(nextFirst) is the NEW INDICE of nextFirst when ADDING.
     *   7. plusOne(nextLast) is the NEW INDICE of nextLast when ADDING.
     * -------------------------------------------------------------------------------
     *   8. plusOne(nextFirst) is the NEW INDICE of nextFirst when REMOVING.
     *   9. minusOne(nextLast) is the NEW INDICE of nextLast when REMOVING.
     */

    /* Creates an empty list  */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**
     * Computes index immediately after a given index.
     * Source for using % to get circular implementation:
     * https://stackoverflow.com/questions/8651965/java-array-traversal-in-circular-manner
     * */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }
    /**
     * Computes index immediately before a given index.
     * */
    private int minusOne(int index) {
        if (index - 1 == -1) {
            return items.length - 1;
        }
        return ((index - 1) % items.length);
    }

    /* Sorts the array in order from first to last */
    private void sort() {
        T[] newArr = (T[]) new Object[items.length];
        for (int i = 0; i < size; i++) {
            newArr[i] = get(i);
        }
        items = newArr;
    }

    /* Re-calibrates the instance's usage factor */
    private void calculateUsageFactor() {
        usageFactor = (double) size / items.length;
    }

    /* Increases size of array */
    private void upSize(int capacity) {
        sort();
        T[] newArr = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newArr, 0, size);
        items = newArr;
        nextFirst = minusOne(0);
        nextLast = size;
    }
    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        if (size == items.length) {
            upSize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
        calculateUsageFactor();
    }
    /* Adds an item of type T to the back of the deque */
    public void addLast(T item) {
        if (size == items.length) {
            upSize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
        calculateUsageFactor();
    }

    /* Downsize the array, assuming current size > given capacity */
    private void downSize(int capacity) {
        sort();
        T[] newArr = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newArr, 0, size);
        items = newArr;
        nextFirst = minusOne(0);
        nextLast = size;
    }
    /* Removes and returns the item at the front of the deque */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else if (usageFactor < 0.25 && items.length >= 16) {
            downSize((int) (items.length * 0.5));
        }
        int firstIndex = plusOne(nextFirst);
        T firstItem = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = plusOne(nextFirst);
        size--;
        calculateUsageFactor();
        return firstItem;
    }
    /* Removes and returns the item at the back of the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else if (usageFactor < 0.25 && items.length >= 16) {
            downSize((int) (items.length * 0.5));
        }
        int lastIndex = minusOne(nextLast);
        T lastItem = items[lastIndex];
        items[lastIndex] = null;
        nextLast = minusOne(nextLast);
        size--;
        calculateUsageFactor();
        return lastItem;
    }

    /* Gets the item at the given index */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int firstIndex = plusOne(nextFirst);
        int answerIndex = (index + firstIndex) % items.length;
        return items[answerIndex];
    }

    /* Returns true if the deque is empty, false otherwise */
    public boolean isEmpty() {
        return (size == 0);
    }

    /* Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(get(i) + " ");
        }
    }
}
