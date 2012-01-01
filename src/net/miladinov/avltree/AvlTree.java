package net.miladinov.avltree;

import net.miladinov.util.Stack;

import java.util.Iterator;
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
                Node<T> left = current.left();
                if (left != null) {
                    return add(data, left);
                } else {
                    current.setLeft(new Node<T>(data));
                    size++;
                    return true;
                }
            case -1:
                Node<T> right = current.right();
                if (right != null) {
                    return add(data, right);
                } else {
                    current.setRight(new Node<T>(data));
                    size++;
                    return true;
                }
            default:
                return false;
        }
    }

    public boolean remove(T data) {
        return contains(data) && removeNodeContaining(data, root);
    }

    private boolean removeNodeContaining(T data, Node<T> searchNode) {
        switch (searchNode.data().compareTo(data)) {
            case 1:
                return ((searchNode.left() != null) && (removeNodeContaining(data, searchNode.left())));
            case -1:
                return ((searchNode.right() != null) && (removeNodeContaining(data, searchNode.right())));
            case 0:
            default:
                removeNode(searchNode);
                return true;
        }
    }

    private void removeNode(Node<T> current) {
        Node<T> parent = current.parent();
        Node<T> left = current.left();
        Node<T> right = current.right();

        if (current.isLeaf()) {
            if (parent == null) {
                root = null;
            } else {
                if (parent.left() == current) {
                    parent.setLeft(null);
                } else if (parent.right() == current) {
                    parent.setRight(null);
                }
            }
        } else if ((left == null || right == null)) {
            if (right == null) {
                if (parent == null) {
                    root = left;
                } else if (parent.left() == current) {
                    parent.setLeft(left);
                } else if (parent.right() == current) {
                    parent.setRight(left);
                }
            } else {
                if (parent == null) {
                    root = right;
                } else if (parent.left() == current) {
                    parent.setLeft(right);
                } else if (parent.right() == current) {
                    parent.setRight(right);
                }
            }
        } else {
            left.rightMost().parent().setRight(null);
            left.rightMost().setRight(current.right());

            if (parent == null) {
                root = left.rightMost();
            } else if (parent.left() == current) {
                parent.setLeft(left.rightMost());
            } else if (parent.right() == current) {
                parent.setRight(left.rightMost());
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
                    current = current.left();
                    break;
                case -1:
                    current = current.right();
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
        for (T data : preorderTraversal()) {
            preorderList.add(data);
        }
        return preorderList;
    }

    public List<T> asInorderList() {
        List<T> inorderList = new LinkedList<T>();
        for (T data : inorderTraversal()) {
            inorderList.add(data);
        }
        return inorderList;
    }

    abstract class TreeTraversal implements Iterable<T> {
        protected Stack<Node<T>> nodeStack;

        public TreeTraversal() {
            nodeStack = new Stack<Node<T>>();
        }
    }

    private Iterable<? extends T> preorderTraversal() {
        return new TreeTraversal() {
            {
                nodeStack.push(root);
            }

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    @Override
                    public boolean hasNext() {
                        return !nodeStack.isEmpty();
                    }

                    @Override
                    public T next() {
                        Node<T> nextNode = nodeStack.pop();

                        // Push in opposite order (right, left) to pop in correct order (left, right)
                        Node<T> right = nextNode.right();
                        if (right != null) nodeStack.push(right);
                        Node<T> left = nextNode.left();
                        if (left != null) nodeStack.push(left);

                        return nextNode.data();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    private Iterable<? extends T> inorderTraversal() {
        return new TreeTraversal() {
            {
                Node<T> current = root;
                while (current != null) {
                    nodeStack.push(current);
                    current = current.left();
                }
            }

            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    @Override
                    public boolean hasNext() {
                        return !nodeStack.isEmpty();
                    }

                    @Override
                    public T next() {
                        Node<T> nextNode = nodeStack.pop();
                        Node<T> current = nextNode.right();
                        while (current != null) {
                            nodeStack.push(current);
                            current = current.left();
                        }
                        return nextNode.data();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

}
