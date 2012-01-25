import net.miladinov.avltree.AvlTree;
import net.miladinov.avltree.TreePrinter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

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
    public void deletingFromLargerTreesShouldNotBeRisky() {
        for (int i = 1; i <= 1024; i++) {
            tree.add(i);
            assertTrue(
                String.format("Tree was not balanced after adding %d", i),
                tree.isBalanced()
            );
        }

        int expectedSize = 1024;
        assertEquals("Tree was not at its expected size", expectedSize, tree.size());

        while (!tree.isEmpty()) {
            Integer rootValue = tree.rootValue();
            tree.remove(rootValue);
            expectedSize--;

            assertNominalSize(expectedSize, rootValue);
            assertEffectiveSize(expectedSize, rootValue);

            System.out.printf(
                "Tree was %s after removing %d\n",
                (tree.isBalanced() ? "balanced" : "unbalanced"),
                rootValue
            );
        }
    }
    
    private void assertNominalSize(int expectedNominalSize, Integer rootValue) {
        assertSize("nominal", expectedNominalSize, nominalSize(), rootValue);
    }
    
    private void assertEffectiveSize(int expectedEffectiveSize, Integer rootValue) {
        assertSize("effective", expectedEffectiveSize, effectiveSize(), rootValue);
    }
    
    private void assertSize(String sizeName, int expectedSize, int actualSize, Integer rootValue) {
        assertEquals(
            String.format(
                "Tree was not at its expected %s size %d after removing %s",
                sizeName,
                expectedSize,
                rootValue
            ),
            expectedSize,
            actualSize
        );
    }

    private int nominalSize() {
        return tree.size();
    }

    private int effectiveSize() {
        return tree.asInorderList().size();
    }
}