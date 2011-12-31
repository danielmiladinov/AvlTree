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

    public boolean add(T i) {
        root = new Node<T>(i);
        size++;
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T i) {
        return root.data().compareTo(i) == 0;
    }

}
