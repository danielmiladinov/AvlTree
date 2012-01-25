import net.miladinov.avltree.AvlTree;
import net.miladinov.avltree.TreePrinter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RemoveRootTests {
    private AvlTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
    }

    @Test
    public void removingTheRootFromALeftHeavyTreePromotesItsLeftChildToBecomeTheNewRoot() {
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(1);

        tree.remove(3);

        assertEquals(
            " - 2 - 4\n" +
                "     \\ 1\n",
            TreePrinter.print(tree)
        );
    }

    @Test
    public void removingTheRootFromARightHeavyTreePromotesItsRightChildToBecomeTheNewRoot() {
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);

        tree.remove(2);

        assertEquals(
            " - 3 - 4\n" +
                "     \\ 1\n",
            TreePrinter.print(tree)
        );
    }
}