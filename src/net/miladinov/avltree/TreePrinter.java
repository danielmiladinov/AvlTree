package net.miladinov.avltree;

import net.miladinov.util.Stack;

public class TreePrinter {
    public static <T extends Comparable<? super T>> String print(AvlTree<T> tree) {
        if (tree.isEmpty()) {
            return "<empty tree>";
        } else {
            return nonEmptyTreeToString(tree);
        }
    }

    private static <T extends Comparable<? super T>> String nonEmptyTreeToString(AvlTree<T> tree) {
        StringBuilder out = new StringBuilder();
        String formatString = "%" + getMaxStringLength(tree) + "s";
        String rightSeparator = " -";
        String leftSeparator = " \\";

        Stack<Node<T>> leftNodes = new Stack<Node<T>>();

        Node<T> current = tree.root();
        int numPrinted = 0;
        int numNodes = tree.size();

        out.append(rightSeparator);
        do {
            out.append(nodeToString(formatString, current));
            numPrinted++;

            if (current.left() != null) {
                leftNodes.push(current.left());
            }

            if (current.right() != null) {
                current = current.right();
                out.append(rightSeparator);
            } else {
                out.append("\n");
                if (!leftNodes.isEmpty()) {
                    current = leftNodes.pop();
                    for (int i = 0; i < current.depth(); i++) {
                        out.append("  ");
                        out.append(String.format(formatString, ""));
                    }
                    out.append(leftSeparator);
                }
            }
        } while (numPrinted < numNodes);

        return out.toString();
    }

    private static <T extends Comparable<? super T>> String nodeToString(String formatString, Node<T> node) {
        return String.format(formatString, node.data().toString());
    }

    private static <T extends Comparable<? super T>> int getMaxStringLength(AvlTree<T> tree) {
        int maxStringLength = 0;
        for (T data : tree.asInorderList()) {
            maxStringLength = Math.max(data.toString().length(), maxStringLength);
        }
        return maxStringLength + 1; // + 1 for a padding between the separator
    }
}
