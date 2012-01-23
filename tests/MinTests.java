import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class MinTests {
    private AvlTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
    }

    @Test
    public void theMinimumOfAnEmptyTreeIsNull() {
        assertNull(tree.min());
    }

    @Test
    public void theMinimumOfATreeOfOneNodeIsThatNodesData() {
        tree.add(17);
        assertEquals((Integer) 17, tree.min());
    }

    @Test
    public void theMinimumOfATreeOfMultipleNodesIsTheMinimumDataValue() {
        tree.add(45);
        tree.add(13);

        assertEquals((Integer) 13, tree.min());
    }

    @Test
    public void theMinimumOfATreeOfVeryManyNodesIsStillTheMinimumDataValue() {
        final int maxInt = 879;
        final int minInt = 231;
        for (int i = maxInt; i >= minInt; i--) {
            tree.add(i);
        }

        assertEquals((Integer) minInt, tree.min());
    }
}

