package hashtable;

import java.util.LinkedList;
import java.util.List;

/**
 * 链表法实现hashtable
 */
public class HashTable {

    private static final int DEFAULT_CAPACITY = 10;

    private List<Integer>[] arr;

    private int size;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        arr = new LinkedList[capacity];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new LinkedList<>();
        }
    }

    public void insert(int value) {
        List<Integer> list = arr[hash(value)];
        if (!list.contains(value)) {
            list.add(value);
        }
        size++;
        if (size > arr.length) {
            rehash();
        }
    }

    public void remove(int value) {
        List<Integer> list = arr[hash(value)];
        if (list.contains(value)) {
            list.remove(Integer.valueOf(value));
            size--;
        }
    }

    public boolean contains(int value) {
        List<Integer> list = arr[hash(value)];
        return list.contains(value);
    }

    public void traverse() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    private int hash(int value) {
        int idx = value % arr.length;
        if (idx < 0) {
            idx += arr.length;
        }
        return idx;
    }

    private void rehash() {
        List<Integer>[] old = arr;
        arr = new LinkedList[2 * old.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new LinkedList<>();
        }

        // 计算器重置
        size = 0;
        for (int i = 0; i < old.length; i++) {
            for (Integer e : old[i]) {
                insert(e);
            }
        }
    }

    public static void main(String[] args) {
        HashTable table = new HashTable(5);
        table.insert(1);
        table.insert(2);
        table.insert(3);
        table.insert(4);
        table.insert(5);
        table.insert(6);
//        table.insert(7);
        table.traverse();
    }
}
