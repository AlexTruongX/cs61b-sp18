public class LinkedListDeque<T> {
    /**
     * Invariants:
     *  1. Sentinel always represents the sentinel/placeholder node.
     *  2. Sentinel.next always points to the first node (if it exists) in the list.
     *  3. Sentinel.prev always points to the last node (if it exists) in the list.
     *  4. size always represents the amount of items in the list.
     *
     */
    private class Node {
        T item;
        Node next;
        Node prev;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /* Constructor for LLDeque */
    public LinkedListDeque() {
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /* Adds an item of type T to the FRONT of the deque. */
    // After adding the NEW node, set the node ahead's .prev EQUAL to new node.
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /* Adds an item of type T to the BACK of the deque
    * 1. Make last node's .next point to new node.
    * 2. Sets the sentinel's prev equal to the new last node
    * */
    public void addLast(T item) {
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }


    /* Remove the first node in the deque. */
    public T removeFirst() {
        if (sentinel.next.item == null) {
            return null;
        }
        size -= 1;
        T firstItem = sentinel.next.item; // Record the first node's item.
        sentinel.next.next.prev = sentinel; // Make the 2nd node's prev point to the sentinel node
        sentinel.next = sentinel.next.next; // Remove the first node from the list.
        return firstItem;
    }
    /** Remove the last node in the deque.
    * 1. Sentinel.prev.prev.next -> make 2nd-to-last node point to the sentinel node
    * 2. Sentinel.prev = sentinel.prev.prev -> remove the last node from LList
    * */
    public T removeLast() {
        if (sentinel.prev.item == null) {
            return null;
        }
        size -= 1;
        T lastItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return lastItem;
    }

    /**
    * Gets the item at the given index.
    *  1. tmpStl is a pointer for LList (prevents mutation)
    * */
    public T get(int index) {
        Node tmpStl = sentinel.next;
        for (int i = 0; i < index; i++) {
            if (tmpStl.next.item == null) {
                return null;
            }
            tmpStl = tmpStl.next;
        }
        return tmpStl.item;
    }
    /* Gets item within deque at given index recursively */
    private T getRecursive(Node p, int index) {
        if (p.item == null) {
            return null;
        } else if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }
    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }

    /* Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        // If there is .next return true, otherwise false.
        return sentinel.next.item == null;

    }
    /* Prints the items in the deque from first to last, separated by a space */
    public void printDeque() {
        Node p = sentinel.next;
        while (p.item != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }
}
