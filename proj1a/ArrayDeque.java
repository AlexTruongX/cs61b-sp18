public class ArrayDeque<T> {
    private double usage_factor = 0.25;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /*  Invariants:
     *   1. nextFirst is the INDICE used when adding a new item to front of the list.
     *   2. nextLast is the INDICE used when adding a new item to the end of the list.
     *   3. Size represents the amount of items in the list.
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

    /** Computes index immediately after a given index.
     *  Circles back to start of underlying array
     *  if it exceeds items.length.
     * */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }
    /* Computes index immediately before a given index.
    *  Circles back to end of underlying array if it
    *  exceeds item.length.
    * */
    private int minusOne(int index) {
        if (index - 1 == -1) {
            return items.length - 1;
        }
        return ((index - 1) % items.length);
    }

    /* Sorts the array in order from first to last */
    private void sort() {
        T[] new_arr = (T[]) new Object[items.length];
        for (int i = 0; i < size; i++) {
            new_arr[i] = get(i);
        }
        items = new_arr;
    }

    /* Re-calibrates the instance's usage factor */
    private void calculate_usage_factor() {
        usage_factor = (double)size/items.length;
    }

    /* Increases size of the array when size == items.length */
    private void upSize(int capacity) {
        sort();
        T[] new_arr = (T[]) new Object[capacity];
        System.arraycopy(items, 0, new_arr, 0, size);
        items = new_arr;
        nextFirst = minusOne(0);
        nextLast = size;
    }
    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        if (size == items.length) {
            upSize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst); // minusOne(nextFirst) is always the indice of the next first item.
        size++;
        calculate_usage_factor();
    }
    /* Adds an item of type T to the back of the deque */
    public void addLast(T item) {
        if (size == items.length) {
            upSize(size * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast); // plusOne(nextLast) is always the last item in the deque
        size++;
        calculate_usage_factor();
    }

    /* Resize method for downsizing array */
    private void downSize (int capacity) {
        sort();
        T[] new_arr = (T[]) new Object[capacity];
        System.arraycopy(items, 0, new_arr, 0, size);
        items = new_arr;
        nextFirst = minusOne(0);
        nextLast = size;
        calculate_usage_factor();
    }
    /* Removes and returns the item at the front of the deque */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else if (usage_factor < 0.25 && items.length >= 16) {
            downSize((int)(items.length * 0.5));
        }
        int first_index = plusOne(nextFirst); // plusOne(nextFirst) is always the first item in the deque
        T first_item = items[first_index];
        items[first_index] = null;
        nextFirst = plusOne(nextFirst);
        size--;
        calculate_usage_factor();
        return first_item;
    }
    /* Removes and returns the item at the back of the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else if (usage_factor < 0.25 && items.length >= 16) {
            downSize((int)(items.length * 0.5));
        }
        int last_index = minusOne(nextLast); // minusOne(nextLast) is always the last item in the deque
        T last_item = items[last_index];
        items[last_index] = null;
        nextLast = minusOne(nextLast);
        size--;
        calculate_usage_factor();
        return last_item;
    }

    /* Gets the item at the given index */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int first_index = plusOne(nextFirst);
        int answer_index = (index + first_index) % items.length;
        return items[answer_index];
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
            System.out.print(items[i] + " ");
        }
    }
}