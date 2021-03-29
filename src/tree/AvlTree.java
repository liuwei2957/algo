package tree;

import java.util.LinkedList;
import java.util.Queue;

public class AvlTree {

    private static final int ALLOW_BALANCE = 1;

    private AvlNode root;

    public void insert(int value) {
        root = insert(value, root);
    }

    public void remove(int value) {
        root = remove(value, root);
    }

    public void traverse() {
        Queue<AvlNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        // last : 表示正在打印的当前行的最右节点，nlast : 表示下一行的最右节点
        AvlNode last = root;
        AvlNode nlast = null;
        while (!queue.isEmpty()) {
            AvlNode node = queue.poll();
            System.out.print(node.data + " ");
            if (node.left != null) {
                queue.offer(node.left);
                nlast = node.left;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nlast = node.right;
            }
            if (node == last) {
                System.out.println();
                last = nlast;
            }
        }
    }

    private int height(AvlNode t) {
        return t == null ? -1 : t.height;
    }

    private AvlNode insert(int value, AvlNode t) {
        if (t == null) {
            return new AvlNode(value, null, null);
        }
        if (value < t.data) {
            t.left = insert(value, t.left);
        } else if (value > t.data) {
            t.right = insert(value, t.right);
        }
        return balance(t);
    }

    private AvlNode remove(int value, AvlNode t) {
        if (t == null) {
            return null;
        }
        if (value < t.data) {
            t.left = remove(value, t.left);
        } else if (value > t.data) {
            t.right = remove(value, t.right);
            // 两个子节点
        } else if (t.left != null && t.right != null) {
            t.data = findMin(t.right).data;
            t.right = remove(t.data, t.right);
        } else {
            t = t.left != null ? t.left : t.right;
        }
        return balance(t);
    }

    private AvlNode findMin(AvlNode t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    private AvlNode balance(AvlNode t) {
        if (t == null) {
            return null;
        }
        if (height(t.left) - height(t.right) > ALLOW_BALANCE) {
            // LL
            if (height(t.left.left) >= height(t.left.right)) {
                t = rightRotate(t);
            } else {
                // LR
                t.left = leftRotate(t.left);
                t = rightRotate(t);
            }
        }

        if (height(t.right) - height(t.left) > ALLOW_BALANCE) {
            // RR
            if (height(t.right.right) >= height(t.right.left)) {
                t = leftRotate(t);
            } else {
                // RL
                t.right = rightRotate(t.right);
                t = leftRotate(t);
            }
        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AvlNode rightRotate(AvlNode t) {
        AvlNode t1 = t.left;
        AvlNode t2 = t1.right;
        t1.right = t;
        t.left = t2;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        t1.height = Math.max(height(t1.left), t.height) + 1;
        return t1;
    }

    private AvlNode leftRotate(AvlNode t) {
        AvlNode t1 = t.right;
        AvlNode t2 = t1.left;
        t1.left = t;
        t.right = t2;
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        t1.height = Math.max(t.height, height(t1.right)) + 1;
        return t1;
    }

    private static class AvlNode {
        public int data;

        public AvlNode left;

        public AvlNode right;

        public int height;

        public AvlNode(int data, AvlNode left, AvlNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        tree.insert(7);
        tree.insert(4);
        tree.insert(13);
        tree.insert(2);
        tree.insert(6);
        tree.insert(11);
        tree.insert(15);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(10);
        tree.insert(12);
        tree.insert(14);
        tree.insert(16);
        tree.insert(8);
        tree.insert(9);
        tree.remove(9);
        tree.traverse();
    }
}
