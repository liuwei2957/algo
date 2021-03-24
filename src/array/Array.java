package array;

public class Array {

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 数组元素个数
     */
    private int size;

    private int[] data;

    public Array() {
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int get(int idx) {
        checkRange(idx);
        return data[idx];
    }

    public int set(int idx, int newValue) {
        checkRange(idx);
        int old = data[idx];
        data[idx] = newValue;
        return old;
    }

    public void add(int idx, int value) {
        if (data.length == size) {
            ensureCapacity(size * 2 + 1);
        }

        for (int i = size; i > idx; i--) {
            data[i] = data[i - 1];
        }
        data[idx] = value;
        size++;
    }

    public boolean add(int value) {
        add(size, value);
        return true;
    }

    public int remove(int idx) {
        checkRange(idx);
        int removed = data[idx];
        for (int i = idx; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }

    public void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }


    private void ensureCapacity(int capacity) {
        if (capacity < size) {
            return;
        }

        int[] newArr = new int[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = data[i];
        }
        data = newArr;

    }

    private void checkRange(int idx) {
        if (idx < 0 || idx >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {
        Array a = new Array();
        a.add(1);
        a.add(3);
        a.add(6);
        a.add(4);
        a.traverse();
    }
}
