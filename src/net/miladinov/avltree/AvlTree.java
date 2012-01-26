package net.miladinov.avltree;

import net.miladinov.util.Stack;

import java.util.*;

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
            boolean addition = add(data, root);
            balance(root);
            return addition;
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

    private void balance(Node<T> node) {
        if (node == null) {
            return;
        }

        balance(node.left());
        balance(node.right());

        int leftHeight = node.leftHeight();
        int rightHeight = node.rightHeight();
        boolean needsBalancing = Math.abs(leftHeight - rightHeight) > 1;

        if (needsBalancing) {
            if (rightHeight > leftHeight) {
                rotateLeft(node);
            } else if (leftHeight > rightHeight) {
                rotateRight(node);
            }
        }
    }

    private void rotateLeft(Node<T> oldNode) {
        Node<T> newNode, parent = oldNode.parent();

        if (oldNode.right().right() != null) {
            newNode = detachRightChildFrom(oldNode);
            if (newNode.left() != null) {
                oldNode.setRight(newNode.left());
            }
            newNode.setLeft(oldNode);
        } else {
            newNode = detachLeftChildFrom(oldNode.right());
            newNode.setRight(detachRightChildFrom(oldNode));
            newNode.setLeft(oldNode);
        }

        setNewChildNodeOf(parent, oldNode, newNode);
    }

    private void rotateRight(Node<T> oldNode) {
        Node<T> newNode, parent = oldNode.parent();

        if (oldNode.left().left() != null) {
            newNode = detachLeftChildFrom(oldNode);
            if (newNode.right() != null) {
                oldNode.setLeft(newNode.right());
            }
            newNode.setRight(oldNode);
        } else {
            newNode = detachRightChildFrom(oldNode.left());
            newNode.setLeft(detachLeftChildFrom(oldNode));
            newNode.setRight(oldNode);
        }

        setNewChildNodeOf(parent, oldNode, newNode);
    }

    private Node<T> detachLeftChildFrom(Node<T> node) {
        Node<T> leftChild = node.left();
        node.setLeft(null);
        return leftChild;
    }

    private Node<T> detachRightChildFrom(Node<T> node) {
        Node<T> rightChild = node.right();
        node.setRight(null);
        return rightChild;
    }

    private void setNewChildNodeOf(Node<T> parent, Node<T> oldChild, Node<T> newChild) {
        if (parent == null) {
            newChild.setParent(null);
            root = newChild;
        } else {
            parent.replace(oldChild).with(newChild);
        }
    }

    public boolean remove(T data) {
        final boolean removal = contains(data) && removeNodeContaining(data, root);
        balance(root);
        return removal;
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
                parent.replace(current).with(null);
            }
        } else if ((left == null || right == null)) {
            if (right == null) {
                if (parent == null) {
                    root = left;
                    root.setParent(null);
                } else {
                    parent.replace(current).with(left);
                }
            } else {
                if (parent == null) {
                    root = right;
                    root.setParent(null);
                } else {
                    parent.replace(current).with(right);
                }
            }
        } else {
            T newData;

            if (current.rightHeight() > current.leftHeight()) {
                newData = deleteMinimumSubNodeOf(right);
            } else {
                newData = deleteMaximumSubNodeOf(left);
            }

            current.setData(newData);
        }

        size--;
    }

    private T deleteMaximumSubNodeOf(Node<T> node) {
        if (node.right() != null) {
            return deleteMaximumSubNodeOf(node.right());
        } else if (node.left() != null) {
            node.parent().replace(node).with(node.left());
        } else {
            node.parent().replace(node).with(null);
        }
        return node.data();
    }

    private T deleteMinimumSubNodeOf(Node<T> node) {
        if (node.left() != null) {
            return deleteMinimumSubNodeOf(node.left());
        } else if (node.right() != null) {
            node.parent().replace(node).with(node.right());
        } else {
            node.parent().replace(node).with(null);
        }
        return node.data();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isBalanced() {
        return isEmpty() || root.isBalanced();
    }

    public T rootValue() {
        return root.data();
    }

    public T min() {
        if (root == null) {
            return null;
        } else {
            return root.leftMost().data();
        }
    }

    public T max() {
        if (root == null) {
            return null;
        } else {
            return root.rightMost().data();
        }
    }

    Node<T> root() {
        return root;
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

    public List<T> asPostorderList() {
        List<T> postorderList = new LinkedList<T>();
        for (T data : postorderTraversal()) {
            postorderList.add(data);
        }
        return postorderList;
    }

    public List<T> asLevelOrderList() {
        List<T> levelOrderList = new LinkedList<T>();
        for (T data : levelOrderTraversal()) {
            levelOrderList.add(data);
        }
        return levelOrderList;
    }

    abstract class TreeTraversal implements Iterable<T> {
        protected Stack<Node<T>> nodeStack;

        public TreeTraversal() {
            nodeStack = new Stack<Node<T>>();
        }

        public void pushOntoStack(Node<T> node) {
            if (node != null) {
                nodeStack.push(node);
            }
        }

        abstract class TreeIterator implements Iterator<T> {
            @Override
            public boolean hasNext() {
                return (!nodeStack.isEmpty());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    private Iterable<? extends T> preorderTraversal() {
        return new TreeTraversal() {
            {
                pushOntoStack(root);
            }

            @Override
            public Iterator<T> iterator() {
                return new TreeIterator() {
                    @Override
                    public T next() {
                        Node<T> nextNode = nodeStack.pop();
                        pushOntoStack(nextNode.right());
                        pushOntoStack(nextNode.left());
                        return nextNode.data();
                    }
                };
            }
        };
    }

    private Iterable<? extends T> inorderTraversal() {
        return new TreeTraversal() {
            {
                pushLeftMostNodesOf(root);
            }

            @Override
            public Iterator<T> iterator() {
                return new TreeIterator() {
                    @Override
                    public T next() {
                        Node<T> nextNode = nodeStack.pop();
                        pushLeftMostNodesOf(nextNode.right());
                        return nextNode.data();
                    }
                };
            }

            private void pushLeftMostNodesOf(Node<T> node) {
                while (node != null && !nodeStack.contains(node)) {
                    nodeStack.push(node);
                    node = node.left();
                }
            }
        };
    }

    private Iterable<? extends T> postorderTraversal() {
        return new TreeTraversal() {
            Map<T, Node<T>> iteratedNodes;

            {
                nodeStack.push(root);
                iteratedNodes = new HashMap<T, Node<T>>();
            }

            @Override
            public Iterator<T> iterator() {
                return new TreeIterator() {
                    @Override
                    public T next() {
                        Node<T> next = null;
                        Node<T> current = nodeStack.peek();

                        while (next == null) {
                            Node<T> left = current.left();
                            Node<T> right = current.right();

                            if (current.isLeaf()) {
                                next = popNextNode();
                            } else {
                                boolean needToBacktrack = true;
                                if (shouldPush(right)) {
                                    nodeStack.push(right);
                                    current = right;
                                    needToBacktrack = false;
                                }
                                if (shouldPush(left)) {
                                    nodeStack.push(left);
                                    current = left;
                                    needToBacktrack = false;
                                }
                                if (needToBacktrack) {
                                    next = popNextNode();
                                }
                            }
                        }

                        return next.data();
                    }

                    private boolean shouldPush(Node<T> node) {
                        return !(node == null || nodeStack.contains(node) || iteratedNodes.containsKey(node.data()));
                    }

                    private Node<T> popNextNode() {
                        Node<T> next = nodeStack.pop();
                        iteratedNodes.put(next.data(), next);
                        return next;
                    }
                };
            }
        };
    }

    private Iterable<? extends T> levelOrderTraversal() {
        return new TreeTraversal() {
            protected Queue<Node<T>> nodeQueue = new LinkedList<Node<T>>();

            @Override
            public Iterator<T> iterator() {
                return new TreeIterator() {
                    {
                        addToQueue(root);
                    }

                    @Override
                    public boolean hasNext() {
                        return !nodeQueue.isEmpty();
                    }

                    @Override
                    public T next() {
                        Node<T> nextNode = nodeQueue.poll();
                        addToQueue(nextNode.left());
                        addToQueue(nextNode.right());
                        return nextNode.data();
                    }
                };
            }

            private void addToQueue(Node<T> node) {
                if (node != null) {
                    nodeQueue.offer(node);
                }
            }
        };
    }
}
