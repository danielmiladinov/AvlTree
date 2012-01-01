package net.miladinov.avltree;

class Node<T extends Comparable<? super T>> {
    private Node<T> parent;
    private Node<T> right;
    private Node<T> left;
    private T data;

    public Node(T data) {
        this(data, null, null, null);
    }

    public Node(T data, Node<T> parent, Node<T> left, Node<T> right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public Node<T> parent() {
        return parent;
    }

    public Node<T> left() {
        return left;
    }

    public Node<T> right() {
        return right;
    }

    public Node<T> rightMost() {
        if (right != null) {
            return right.rightMost();
        } else {
            return this;
        }
    }

    public T data() {
        return data;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public boolean isLeaf() {
        return ((left == null) && (right == null));
    }
}
