package net.miladinov.avltree;

class Node<T extends Comparable<? super T>> implements Comparable<T> {
    private Node<T> right;
    private Node<T> left;
    private T data;

    public Node(T data) {
        this(data, null, null);
    }

    public Node(T data, Node<T> left, Node<T> right) {
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

    public T data() {
        return data;
    }

    @Override
    public int compareTo(T o) {
        return data.compareTo(o);
    }
}
