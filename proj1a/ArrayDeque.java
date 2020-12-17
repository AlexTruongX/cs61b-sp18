public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

/*  Invariants:
 *   nextFirst + 1 = first item in the deque
 *   nextLast - 1 = last item in the deque
 *   size is always the amount of items in the list.
 */
    /* ArrayDeque Constructor */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 6;
    }

    private double capacity() {
        double usage_factor = (double)size/items.length; // Need  to cast the ints into doubles, otherwise may get 0.
        if (items.length < 16 && (usage_factor < 0.25)) {
            return items.length; // Do nothing if array size is <16 and
        } else if (usage_factor > 0.25) {
            System.out.println("Upsizing!");
            return 2;
        } else {
            System.out.println("Downsizing!");
            updateNext();
            return 0.5;
        }
/*      1. Performing a check on item deletion in array -> resize if extra space is unnecessary
            1a. If there are items at the end of the array (size 3, but array.length = 1000)
            1a cont. THEN -> Then resize and move those items into the new array (condensing)
        2. Array.length < 16 -> usage factor can be arbitrarily low
        3. Array.length >= 16 -> usage factor = 0.25; */
    }
    /* Resizes arrays  */
    private void resize() {
        double newCapacity = items.length * capacity();
        T[] tmp_arr = (T[]) new Object[(int)newCapacity];
        System.arraycopy(items, 0, tmp_arr, 0, size);
        items = tmp_arr;
    }

    /* Updates nextFirst and nextLast if they reach their ends (Circular array implementation) */
    private void updateNext() {
        double usage_factor = (double)size/items.length;
        if (size == items.length || (usage_factor > 0.25 && items.length >= 16)) {
            nextLast = size; // Re-adjust the nextLast to be the indice of the first null value in new array;
            resize();
            nextFirst = items.length-1; // Re-adjust nextFirst to the new array's very last indice
        } update_next_first();
          update_next_last();
    }
    private void update_next_first() {
        if (nextFirst == -1) {
            nextFirst = items.length - 1; // Sets nextFirst to end of the list
        } else if (nextFirst == items.length+1) {
            nextFirst = 0; // If you're at the end of the list and deleting, reset back to 0.
        }
    }
    private void update_next_last() {
        if (nextLast == items.length) {
            nextLast = 0;
        }
    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        updateNext();
        items[nextFirst] = item;
        nextFirst -= 1; // Null case because -> nextFirst = 0 -> -1 -> code breaks;
        size += 1;         // Need to move and resize array if no space.
    }

    /* Adds an item of type T to the back of the deque */
    public void addLast(T item) {
        updateNext();
        items[nextLast] = item;
        nextLast += 1;
        // Need to move and resize array if no space.
        size += 1;
    }

    /** Removes and returns the item at the front of the deque
     * If no such item exists, returns null.
     * [$] Implemented anti-loitering condition
     *                                                      */
    public T removeFirst() {
        double usage_factor = (double)size/items.length;
        if (items[nextFirst+1] == null) {
            return null;
        } else if (usage_factor < 0.25) {
            resize();
        } size -= 1;
        T first_item = items[nextFirst+1];
        items[nextFirst + 1] = null;
        nextFirst = nextFirst+1;
        return first_item;
    }

    /** Removes and returns the item at the back of the deque
     * If no such item exists, returns null.
     * [$] Implemented anti-loitering condition
     *                                                      */
    public T removeLast() {
        double usage_factor = (double)size/items.length;
        if (items[nextLast-1] == null) {
            return null;
        } else if (usage_factor < 0.25) {
            resize();
        } size -= 1;
        T last_item = items[nextLast-1];
        items[nextLast - 1] = null;
        nextLast = nextLast - 1;
        return last_item;
    }

    /* Gets the item at the given index */
    public T get (int index) {
        if (items[index] == null) {
            return null;
        } return items[index];
    }

    /* Returns the number of items in the deque
    [*] Implemented in constant time using caching */
    public int size() {
        return size;
    }

    /* Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } return false;
    }

    /* Prints the items in the deque from first to last, separated by a space */
    // [!!!] NEED TO FIND MORE TIME EFFICIENT WAY TO PRINT FOR LARGER ARRAYS
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null ) {
                System.out.print(items[i] + " ");
            }
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            test.addLast(i);
        }
        for (int i = 0; i < 900; i++) {
            test.removeLast();
        }
        test.addFirst(10);
        test.addFirst(20);
        test.printDeque();
    }
}
