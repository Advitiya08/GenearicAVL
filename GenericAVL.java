package DSA.topics;

import java.util.*;

class AVLNode<T> {
    List<T> values;
    int height;
    AVLNode<T> left, right;

    AVLNode(T value) {
        this.values = new ArrayList<>();
        this.values.add(value);
        this.height = 1;
    }

    T getKey() {
        return values.get(0);
    }
}

public class GenericAVL<T> {
    private AVLNode<T> root;
    private final Comparator<T> comparator;
    private final Comparator<T> duplicateSort;

    public GenericAVL(Comparator<T> comparator) {
        this(comparator, null);
    }

    public GenericAVL(Comparator<T> comparator, Comparator<T> duplicateSort) {
        this.comparator = comparator;
        this.duplicateSort = duplicateSort;
    }

    public void insert(T value) {
        root = insert(value, root);
    }

    public void delete(T value) {
        root = delete(value, root);
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private AVLNode<T> insert(T value, AVLNode<T> node) {
        if (node == null) return new AVLNode<>(value);

        int cmp = comparator.compare(value, node.getKey());

        if (cmp < 0) {
            node.left = insert(value, node.left);
        } else if (cmp > 0) {
            node.right = insert(value, node.right);
        } else {
            node.values.add(value);
            if (duplicateSort != null) node.values.sort(duplicateSort);
            return node;
        }

        return balance(node);
    }

    private AVLNode<T> delete(T value, AVLNode<T> node) {
        if (node == null) return null;

        int cmp = comparator.compare(value, node.getKey());

        if (cmp < 0) {
            node.left = delete(value, node.left);
        } else if (cmp > 0) {
            node.right = delete(value, node.right);
        } else {
            node.values.remove(value);
            if (!node.values.isEmpty()) return node;

            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                AVLNode<T> successor = minValueNode(node.right);
                node.values = successor.values;
                node.right = delete(successor.getKey(), node.right);
            }
        }

        return balance(node);
    }

    private AVLNode<T> minValueNode(AVLNode<T> node) {
        AVLNode<T> current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private int height(AVLNode<T> node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(AVLNode<T> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode<T> balance(AVLNode<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rotateRight(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return rotateLeft(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    private AVLNode<T> rotateRight(AVLNode<T> y) {
        AVLNode<T> x = y.left;
        AVLNode<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> x) {
        AVLNode<T> y = x.right;
        AVLNode<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private void inorder(AVLNode<T> node) {
        if (node != null) {
            inorder(node.left);
            for (T val : node.values) System.out.print(val + " ");
            inorder(node.right);
        }
    }
}
