public class LinkedListDeque<T> {
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

    public LinkedListDeque() {
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        // First item is at sentinel.next
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /* Adds an item of type T to the FRONT of the deque. */
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next; // After adding the new node, set the node ahead's .prev = to new node.
        size += 1;
    }

    /* Adds an item of type T to the BACK of the deque */
    public void addLast(T item) {
        // Sentinel.prev points to the last node in the list
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel); // Make last node's .next point to new node.
        sentinel.prev = sentinel.prev.next; // Sets the sentinel's prev equal to the new last node;
        size += 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (sentinel.next.item == null) { // Checks if there exists a .next, if there is return false, otherwise true.
            return true;
        }
        return false;
    }

    /* Returns the number of items in the deque */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space */
    public void printDeque() {
        Node p = sentinel.next;
        while (p.item != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /* Remove the first node in the deque, taking constant time. If item doesn't exist, return null. */
    public T removeFirst() {
        if (sentinel.next.item == null) {
            return null;
        }
        size -= 1;
        T first_item = sentinel.next.item; // Record the first node's item.
        sentinel.next.next.prev = sentinel; // Make the 2nd node's previous equal to the sentinel node
        sentinel.next = sentinel.next.next; // Remove the first node from the list.
        return first_item;
    }

    /* Remove the last node in the deque, taking constant time. If item doesn't exist, return null. */
    public T removeLast() {
        if (sentinel.prev.item == null) {
            return null;
        }
        size -= 1;
        T last_item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel; // Make the 2nd to last node's next point to the sentinel node.
        sentinel.prev = sentinel.prev.prev; // Remove the last node from the list.
        return last_item;
    }

    /*
     * Gets the item at the given index, where 0 is the front (sentinel.next),
     * 1 is the next item, and so forth. If no such item exists, return null.
     */
    public T get(int index) {
        Node tmp_stl = sentinel.next;
        for (int i = 0; i < index; i++) {
            if (tmp_stl.next.item == null) {
                return null;
            }
            tmp_stl = tmp_stl.next;
        }
        return tmp_stl.item;
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

    public static void main(String[] args) {
        LinkedListDeque<String> test = new LinkedListDeque<>();
        test.addFirst("front");
        test.addLast("back");
        test.addFirst("l0ser");
    }
}