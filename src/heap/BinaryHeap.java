package heap;

import java.util.Arrays;

public class BinaryHeap {

    private static final int DEFAULT_CAPACITY = 10;

    private int[] arr;

    private int size;

    public BinaryHeap(int capacity) {
        arr = new int[capacity];
    }

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int value) {
        if (size == arr.length - 1) {
            resize(arr.length * 2 + 1);
        }
        size++;
        int hole = size;

        // 上浮
        while (value < arr[hole / 2] && hole > 0) {
            arr[hole] = arr[hole / 2];
            hole /= 2;
        }
        arr[hole] = value;
    }

    public int deleteMin() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        int min = arr[1];
        arr[1] = arr[size];
        arr[size] = 0;
        size--;
        percolateDown(1);
        return min;
    }

    /**
     * 下浮
     *
     * @param hole
     */
    private void percolateDown(int hole) {
        while (size > hole) {
            int child = hole * 2;

            if ((child + 1) <= size && arr[child + 1] < arr[child]) {
                child++;
            }

            if (arr[hole] > arr[child]) {
                int tmp = arr[hole];
                arr[hole] = arr[child];
                arr[child] = tmp;
            } else {
                break;
            }
            hole = child;
        }

    }

    private void resize(int capacity) {
        int[] newArr = new int[capacity];
        for (int i = 1; i <= size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public void traverse() {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap(5);
        heap.insert(10);
        heap.insert(7);
        heap.insert(12);
        heap.insert(5);
        heap.insert(6);
        heap.insert(1);
        heap.insert(11);
        heap.traverse();
    }
}
