package hashtable;

/**
 * 探测法实现hashtable
 */
public class HashTable2 {

    private static final int DEFAULT_CAPACITY = 10;

    private HashEntry[] arr;

    private int size;

    public HashTable2(int size) {
        this.size = size;
        arr = new HashEntry[size];
    }

    public HashTable2() {
        this(DEFAULT_CAPACITY);
    }

    public boolean contains(int value) {
        int curPos = findPos(value);
        return arr[curPos] != null && !arr[curPos].isDeleted;
    }

    public void insert(int value) {
        int curPos = findPos(value);
        if (arr[curPos] != null && !arr[curPos].isDeleted) {
            return;
        }
        arr[curPos] = new HashEntry(value);
        size++;
        if (size > arr.length / 2) {
            rehash();
        }
    }

    public void remove(int value) {
        int curPos = findPos(value);
        arr[curPos].isDeleted = true;
    }

    /**
     * 找出元素的位置
     *
     * @param value
     * @return
     */
    private int findPos(int value) {
        int offset = 1;
        int curPos = hash(value);
        while (arr[curPos] != null && arr[curPos].data != value) {
            curPos += offset;
            offset += 2;
            if (curPos >= arr.length) {
                curPos -= arr.length;
            }
        }
        return curPos;
    }

    private int hash(int value) {
        int idx = value % arr.length;
        if (idx < 0) {
            idx += arr.length;
        }
        return idx;
    }

    private void rehash() {
        HashEntry[] old = arr;
        arr = new HashEntry[old.length * 2];

        size = 0;
        for (int i = 0; i < old.length; i++) {
            if (old[i] != null && !old[i].isDeleted) {
                insert(old[i].data);
            }
        }
    }

    public void traverse() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    private static class HashEntry {

        public int data;

        public boolean isDeleted;

        public HashEntry(int data) {
            this.data = data;
            this.isDeleted = false;
        }

        @Override
        public String toString() {
            return data + ":" + isDeleted;
        }
    }

    public static void main(String[] args) {
        HashTable2 table2 = new HashTable2(5);
        table2.insert(2);
        table2.insert(3);
        table2.insert(4);
        table2.insert(5);
        table2.insert(16);
        table2.insert(14);
        table2.insert(23);
        table2.traverse();
    }
}
