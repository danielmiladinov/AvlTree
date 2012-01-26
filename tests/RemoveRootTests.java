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

    @Test
    public void deletingTheRootFromANonTrivialTreeShouldNotCompletelyUnbalanceTheTree() {
        tree.add(50);
        tree.add(25);
        tree.add(75);
        tree.add(15);
        tree.add(35);
        tree.add(60);
        tree.add(90);
        tree.add(1);
        tree.add(20);
        tree.add(30);
        tree.add(45);
        tree.add(55);
        tree.add(70);
        tree.add(80);
        tree.add(100);

        tree.remove(50);

        assertEquals(
            " -  45 -  75 -  90 - 100\n" +
            "                   \\  80\n" +
            "             \\  60 -  70\n" +
            "                   \\  55\n" +
            "       \\  25 -  35\n" +
            "                   \\  30\n" +
            "             \\  15 -  20\n" +
            "                   \\   1\n",
            TreePrinter.print(tree)
        );
    }

    @Test
    public void deletingFromLargerTreesShouldNotBeRisky() {
        int expectedSize = 0;
        for (int i = 1; i <= 1024; i++) {
            tree.add(i);
            expectedSize++;
            assertEquals(
                String.format("Tree was not at expected nominal size %d after adding %d", expectedSize, i),
                expectedSize,
                nominalSize()
            );
            assertEquals(
                String.format("Tree was not at expected effective size %d after adding %d", expectedSize, i),
                expectedSize,
                effectiveSize()
            );
        }

        assertEquals("Tree was not at its expected nominal size", expectedSize, nominalSize());
        assertEquals("Tree was not at its expected effective size", expectedSize, effectiveSize());

        while (!tree.isEmpty()) {
            Integer rootValue = rootValue();
            tree.remove(rootValue);
            expectedSize--;

            assertNominalSize(expectedSize, rootValue);
            assertEffectiveSize(expectedSize, rootValue);
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

    private Integer rootValue() {
        return tree.asLevelOrderList().get(0);
    }
}