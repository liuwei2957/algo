package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    private BinaryNode root;

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(int value) {
        return contains(value, root);
    }

    public int findMin() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return findMin(root).data;
    }

    public int findMax() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return findMax(root).data;
    }

    public void insert(int value) {
        root = insert(value, root);
    }

    public void remove(int value) {
        root = remove(value, root);
    }

    public void traverse() {
        Queue<BinaryNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        // last : 表示正在打印的当前行的最右节点，nlast : 表示下一行的最右节点
        BinaryNode last = root;
        BinaryNode nlast = null;
        while (!queue.isEmpty()) {
            BinaryNode node = queue.poll();
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

    private boolean contains(int value, BinaryNode t) {
        if (t == null) {
            return false;
        }

        if (value < t.data) {
            return contains(value, t.left);
        } else if (value > t.data) {
            return contains(value, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode findMin(BinaryNode t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);

    }

    private BinaryNode findMax(BinaryNode t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    /**
     * 插入一个节点，返回根节点
     *
     * @param value
     * @param t
     * @return
     */
    private BinaryNode insert(int value, BinaryNode t) {
        if (t == null) {
            return new BinaryNode(value, null, null);
        }
        if (value < t.data) {
            t.left = insert(value, t.left);
        } else if (value > t.data) {
            t.right = insert(value, t.right);
        }
        return t;
    }

    /**
     * 返回新树根
     * @param value
     * @param t
     * @return
     */
    private BinaryNode remove(int value, BinaryNode t) {
        if (t == null) {
            return null;
        }
        // 叶子节点
        if (value < t.data) {
            t.left = remove(value, t.left);
        } else if (value > t.data) {
            t.right = remove(value, t.right);
            // 两个子节点
        } else if (t.left != null && t.right != null) {

            // 右子树最小节点替换被删节点，并删除这个最小节点
            t.data = findMin(t.right).data;
            t.right = remove(t.data, t.right);
            // 一个子节点
        } else {
            t = t.left != null ? t.left : t.right;
        }
        return t;
    }

    private static class BinaryNode {
        public int data;

        public BinaryNode left;

        public BinaryNode right;

        public BinaryNode(int data, BinaryNode left, BinaryNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(33);
        tree.insert(16);
        tree.insert(50);
        tree.insert(13);
        tree.insert(18);
        tree.insert(34);
        tree.insert(58);
        tree.insert(15);
        tree.insert(17);
        tree.insert(25);
        tree.insert(51);
        tree.insert(66);
        tree.insert(19);
        tree.insert(27);
        tree.insert(55);
        tree.remove(55);
        tree.remove(13);
        tree.remove(18);
        tree.traverse();
    }

}
