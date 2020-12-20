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
    /* Creates an empty list  */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void reorganize() {
        T[] tmp_arr = (T[]) new Object[items.length];
        int num_elements_first = 0;
        int num_elements_back = 0;
        int last_index = 0;
        if (nextFirst+1 > 4) { // Copies first elements at the END of the underlying array (1st first elems)
            num_elements_first = ((items.length-1)-nextFirst);
            System.arraycopy(items, nextFirst+1, tmp_arr, 0, num_elements_first);
            nextFirst = 0;
        }
        if (nextFirst < 4) { // Copies first elements at the START of the underlying array (2nd first elems)
            int temp = (5-(nextFirst));
            System.arraycopy(items, nextFirst, tmp_arr, num_elements_first, temp);
            last_index = temp + num_elements_first;
        }
        if (nextLast >=  5) { // Copies any elements at START of underlying array (1st last elems)
            num_elements_back = (nextLast-5);
            System.arraycopy(items, 5, tmp_arr, last_index, num_elements_back);
        }
        if (nextLast < 5) { // Copies any elements at END of underlying array (2nd last elems)
            int temp = (5-nextLast);
            int start_index = num_elements_first + last_index + num_elements_back;
            System.arraycopy(items, nextLast, tmp_arr, start_index, temp);
        }
        items = tmp_arr;
    }

    private double capacity() {
        double usage_factor = (double) size / items.length; // Need  to cast the ints into doubles, otherwise may get 0.
        if (items.length < 16 && (usage_factor < 0.25)) {
            return items.length; // Do nothing if array size is <16 and
        } else if (usage_factor > 0.25) {
            return 2;
        } else {
            return 0.5;
        }
    }

    private void resize() {
        // Before resizing operation, need to sort the array.
        reorganize();
        double newCapacity = items.length * capacity();
        T[] tmp_arr = (T[]) new Object[(int)newCapacity];
        System.arraycopy(items, 0, tmp_arr, 0, size);
        items = tmp_arr;
    }

    /* Updates nextFirst */
    private void update_next_first() {
        if (nextFirst == -1) {
            nextFirst = items.length - 1; // Continues at end of underlying array.
        }
    }
    private void update_next_last() {
        if (nextLast > items.length - 1) {
            nextLast = 0;
        }
    }
    public void update_next_both() {
        if (size == items.length) {
            int nextLast_start_pos = items.length;
            resize();
            nextLast = nextLast_start_pos;
            nextFirst = items.length - 1;
        } update_next_first();
        update_next_last();
    }

    /* Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        size += 1;
        items[nextFirst] = item;
        nextFirst -= 1;
        update_next_both();
    }

    public void addLast(T item) {
        size += 1;
        items[nextLast] = item;
        nextLast += 1;
        update_next_both();
    }

    /* Returns true if deque is empty, false otherwise */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* Gets the item at the given index */
    public T get(int index) {
        if (items[index] == null) {
            return null;
        } return items[index];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(-1);
        test.addLast(1000);
        for (int i = 0; i < 100; i++) {
            test.addFirst(i);
        }
        for (int i = 200; i < 300; i++) {
            test.addLast(i);
        }
        test.isEmpty();
    }

}
