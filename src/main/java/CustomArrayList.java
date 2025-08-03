import java.util.Arrays;

class CustomArrayList<E> {
    private Object[] data;
    private int size;

    public CustomArrayList() {
        this.data = new Object[16]; // начальная ёмкость
    }

    public void add(E element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    public E get(int index) {
        rangeCheck(index);
        return (E)data[index];
    }

    public E remove(int index) {
        rangeCheck(index);
        E result = (E)data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        data[--size] = null;
        return result;
    }

    public void addAll(CustomArrayList<E> list) {
        ensureCapacity(size + list.size);
        System.arraycopy(list.data, 0, data, size, list.size);
        size += list.size;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= data.length) return;
        int newCapacity = data.length + (data.length >> 1); // увеличить вдвое примерно
        data = Arrays.copyOf(data, newCapacity);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}