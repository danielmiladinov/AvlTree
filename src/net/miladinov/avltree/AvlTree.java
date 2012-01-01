package net.miladinov.avltree;

import java.util.LinkedList;
import java.util.List;

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
            return true;
        } else {
            return add(data, root);
        }
    }

    private boolean add(T data, Node<T> current) {
        switch (current.data().compareTo(data)) {
            case 1:
                Node<T> right = current.right();
                if (right != null) {
                    return add(data, right);
                } else {
                    current.setRight(new Node<T>(data));
                    size++;
                    return true;
                }
            case -1:
                Node<T> left = current.left();
                if (left != null) {
                    return add(data, left);
                } else {
                    current.setLeft(new Node<T>(data));
                    size++;
                    return true;
                }
            default:
                return false;
        }
    }

    public boolean remove(T data) {
        return contains(data) && remove(data, root, null);
    }

    private boolean remove(T data, Node<T> current, Node<T> parent) {
        switch (current.data().compareTo(data)) {
            case 1:
                Node<T> right = current.right();
                return ((right != null) && (remove(data, right, current)));
            case -1:
                Node<T> left = current.left();
                return ((left != null) && (remove(data, left, current)));
            case 0:
            default:
                unlink(current, parent);
                return true;
        }
    }

    private void unlink(Node<T> current, Node<T> parent) {
        if (current.isLeaf()) {
            if (parent != null) {
                if (parent.left() == current) {
                    parent.setLeft(null);
                } else if (parent.right() == current) {
                    parent.setRight(null);
                }
            } else {
                root = null;
            }
        } else {
            Node<T> left = current.left();
            Node<T> right = current.right();

            if ((left == null || right == null)) {
                if (right == null) {
                    if (parent.left() == current) {
                        parent.setLeft(left);
                    } else if (parent.right() == current) {
                        parent.setRight(left);
                    } else {
                        root = left;
                    }
                } else {
                    if (parent.left() == current) {
                        parent.setLeft(right);
                    } else if (parent.right() == current) {
                        parent.setRight(right);
                    } else {
                        root =right;
                    }
                }
            } else {
                Node<T> parentOfRightmostOfLeft = current;
                Node<T> rightmostOfLeft = left;

                while (parentOfRightmostOfLeft.right() != null) {
                    rightmostOfLeft = parentOfRightmostOfLeft.right();
                    parentOfRightmostOfLeft = rightmostOfLeft;
                }

                parentOfRightmostOfLeft.setRight(null);
                rightmostOfLeft.setRight(current.right());

                if (parent.left() == current) {
                    parent.setLeft(rightmostOfLeft);
                } else if (parent.right() == current) {
                    parent.setRight(rightmostOfLeft);
                } else {
                    root = rightmostOfLeft;
                }
            }
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

    public List<T> asPreorderList() {
        List<T> preorderList = new LinkedList<T>();
        populatePreorder(preorderList, root);
        return preorderList;
    }

    private void populatePreorder(List<T> preorderList, Node<T> current) {
        if (current != null) {
            preorderList.add(current.data());
            populatePreorder(preorderList, current.right());
            populatePreorder(preorderList, current.left());
        }
    }
}
