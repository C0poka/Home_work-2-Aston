import java.util.Objects;

class CustomHashSet<E> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;
    private Entry<E>[] table;
    private int size;
    private int threshold;

    @SuppressWarnings("unchecked")
    public CustomHashSet() {
        this.table = (Entry<E>[])new Entry<?>[DEFAULT_CAPACITY];
        this.threshold = (int)(DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
    }

    public boolean add(E element) {
        Objects.requireNonNull(element);
        int index = hash(element.hashCode()) % table.length;
        for (Entry<E> e = table[index]; e != null; e = e.next) {
            if (Objects.equals(e.element, element)) {
                return false; // Элемент уже существует
            }
        }
        addToBucket(index, element);
        return true;
    }

    public E remove(Object o) {
        if (o == null) return null;
        int index = hash(o.hashCode()) & (table.length - 1);
        Entry<E> prev = null;
        for (Entry<E> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.element.equals(o)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.element;
            }
            prev = entry;
        }
        return null;
    }

    private void addToBucket(int bucketIndex, E element) {
        Entry<E> newEntry = new Entry<>(element, table[bucketIndex]);
        table[bucketIndex] = newEntry;
        if (++size > threshold) resize();
    }

    private void resize() {
        Entry<E>[] oldTable = table;
        int newCapacity = oldTable.length * 2;
        Entry<E>[] newTable = new Entry[newCapacity];
        threshold = (int)(newCapacity * DEFAULT_LOAD_FACTOR);
        rehash(newTable, oldTable);
        table = newTable;
    }

    private void rehash(Entry<E>[] newTable, Entry<E>[] oldTable) {
        for (Entry<E> entry : oldTable) {
            while (entry != null) {
                int newIndex = hash(entry.element.hashCode()) % newTable.length;
                Entry<E> next = entry.next;
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;
                entry = next;
            }
        }
    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private static class Entry<E> {
        E element;
        Entry<E> next;

        Entry(E element, Entry<E> next) {
            this.element = element;
            this.next = next;
        }
    }
}