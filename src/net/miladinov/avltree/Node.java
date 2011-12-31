package net.miladinov.avltree;

class Node<T extends Comparable<? super T>> implements Comparable<T> {
    private Node<T> parent;
    private Node<T> right;
    private Node<T> left;
    private T data;

    public Node(T data) {
        this(null, data);
    }

    public Node(Node<T> parent, T data) {
        this(parent, data, null, null);
    }

    public Node(Node<T> parent, T data, Node<T> left, Node<T> right) {
        this.parent = parent;
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Node<T> left() {
        return left;
    }

    public Node<T> right() {
        return right;
    }

    public Node<T> parent() {
        return parent;
    }

    public T data() {
        return data;
    }

    @Override
    public int compareTo(T o) {
        return data.compareTo(o);
    }
}
