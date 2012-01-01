package net.miladinov.avltree;

public class AvlTree<T extends Comparable<? super T>> {
    private Node<T> root;
    private int size = 0;

    public AvlTree() {
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean add(T data) {
        if (isEmpty()) {
            root = new Node<T>(data);
            size++;
            return false;
        } else {
            return add(data, root, null);
        }
    }

    private boolean add(T data, Node<T> current, Node<T> parent) {
        switch (current.data().compareTo(data)) {
            case 1:
                Node<T> right = current.right();
                if (right != null) {
                    return add(data, right, current);
                } else {
                    current.setRight(new Node<T>(data));
                    size++;
                    return true;
                }
            case -1:
                Node<T> left = current.left();
                if (left != null) {
                    return add(data, left, current);
                } else {
                    current.setLeft(new Node<T>(data));
                    size++;
                    return true;
                }
            default:
                return false;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T data) {
        if (isEmpty()) {
            return false;
        }

        Node<T> current = root;

        while (current != null) {
            switch (current.data().compareTo(data)) {
                case 1:
                    current = current.right();
                    break;
                case -1:
                    current = current.left();
                    break;
                case 0:
                default:
                    return true;
            }
        }

        return false;
    }
}
