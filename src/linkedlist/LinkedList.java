package linkedlist;

public class LinkedList {

    /**
     * 链表元素个数
     */
    private int size;

    /**
     * head,tail是哨兵节点，不属于链表节点
     */
    private Node head;

    private Node tail;

    public LinkedList() {
        head = new Node(0, null, null);
        tail = new Node(0, head, null);
        head.next = tail;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int idx, int value) {
        addBefore(getNode(idx), value);
    }

    public boolean add(int value) {
        add(size, value);
        return true;
    }

    /**
     * 在p之前插入
     * @param p
     * @param value
     */
    public void addBefore(Node p, int value) {
        Node newNode = new Node(value, p.prev, p);
        p.prev.next = newNode;
        p.prev = newNode;
        size++;
    }

    public int remove(int idx) {
        Node p = getNode(idx);
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
        return p.data;
    }

    public int get(int idx) {
        return getNode(idx).data;
    }

    public int set(int idx, int newValue) {
        Node node = getNode(idx);
        node.data = newValue;
        return node.data;
    }

    public void traverse() {
        Node p = head.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public Node getNode(int idx) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException();
        }

        Node p;
        if (idx < size / 2) {
            p = head;
            for (int i = 0; i <= idx; i++) {
                p = p.next;
            }
        } else {
            p = tail;
            for (int i = size - 1; i >= idx; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    private static class Node{
        public int data;

        public Node prev;

        public Node next;

        public Node(int data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(2);
        list.traverse();
    }
}
