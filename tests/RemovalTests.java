import net.miladinov.avltree.AvlTree;
import net.miladinov.avltree.TreePrinter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RemovalTests {
    private AvlTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
        for (int i = 1; i <= 7; i++) {
            tree.add(i);
        }
    }

    @Test
    public void removingFromTheRightResultsInABalancedTree() {
        tree.remove(7);
        tree.remove(6);
        tree.remove(5);
        assertEquals(
            " - 2 - 4\n" +
            "         \\ 3\n" +
            "     \\ 1\n",
            TreePrinter.print(tree)
        );
    }

    @Test
    public void removingFromTheLeftResultsInABalancedTree() {
        tree.remove(1);
        tree.remove(2);
        tree.remove(3);
        assertEquals(
            " - 6 - 7\n" +
            "     \\ 4 - 5\n",
            TreePrinter.print(tree)
        );
    }
}
