import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean contains(T x) {
        for (T i : items) {
            if (x.equals(i)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("Cannot add null!");
        } else if (contains(x)) {
            return;
        }
        items[size] = x;
        size++;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    /* Slow Approach */
    /*
    @Override
    public String toString() {
        String returnString = "{";
        for (int i = 0; i < size - 1; i += 1) {
            returnString += items[i].toString(); // Combining notString to a String, Java implicitly calls toString()
            returnString += ", ";
        }
        returnString += items[size - 1];
        returnString += "}";
        return returnString;
    } */

    /* Fast Approach */
    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i += 1) {
            returnSB.append(items[i].toString()); // Combining notString to a String, Java implicitly calls toString()
            returnSB.append(", ");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");
        return returnSB.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true; // Optimization
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()) {
            return false;
        }
        for (T item : this) {
            if (!o.contains(item)) {
                return false;
            }
         }
        return true;
    }


// For ArraySet implementation and exception testing */
/*    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
//        System.out.println(s.contains("horse"));
//        System.out.println(s.size());

        Set<String> s2 = new HashSet<>();
        s2.add(null);
        System.out.println(s2.contains(null));
    }*/

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        System.out.println(aset);

/*     --* Ugly iteration *--
       Iterator<Integer> aseer = aset.iterator();
       while (aseer.hasNext()) {
           int i = aseer.next();
           System.out.println(i);
       }

        for (int i : aset) {
            System.out.println(i);
        } */

        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);
        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(null));
        System.out.println(aset.equals("fish"));
    }
}
