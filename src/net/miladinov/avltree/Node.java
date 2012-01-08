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

    public Node<T> leftMost() {
        if (left != null) {
            return left().leftMost();
        } else {
            return this;
        }
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

    public void setParent(Node<T> parent) {
        this.parent = parent;

        if (this.left != null && this.left.parent != this) {
            this.left.setParent(this);
        }

        if (this.right != null && this.right.parent != this) {
            this.right.setParent(this);
        }
    }

    public void setRight(Node<T> right) {
        if (!((right == null) || (data.compareTo(right.data()) == -1))) {
            throw new RuntimeException("Cannot set " + right.data + " to be right of " + data);
        }

        this.right = right;

        if (this.right != null) {
            this.right.setParent(this);
        }
    }

    public void setLeft(Node<T> left) {
        if (!((left == null) || (data.compareTo(left.data()) == 1))) {
            throw new RuntimeException("Cannot set " + right.data + " to be left of " + data);
        }

        this.left = left;

        if (this.left != null) {
            this.left.setParent(this);
        }
    }

    public boolean isLeaf() {
        return ((left == null) && (right == null));
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    public int height() {
        return 1 + Math.max(leftHeight(), rightHeight());
    }

    public int depth() {
        if (parent == null) {
            return 0;
        } else {
            return 1 + parent.depth();
        }
    }

    public ChildNodeReplacement replace(Node<T> oldChild) {
        return new ChildNodeReplacement(oldChild);
    }

    public class ChildNodeReplacement {
        private Node<T> oldChild;

        public ChildNodeReplacement(Node<T> oldChild) {
            if ((oldChild == left || oldChild == right)) {
                this.oldChild = oldChild;
            }
        }

        public void with(Node<T> newChild) {
            if (oldChild == left) {
                setLeft(newChild);
            } else if (oldChild == right) {
                setRight(newChild);
            }
        }
    }
}
