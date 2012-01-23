import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class MaxTests {
    private AvlTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
    }

    @Test
    public void theMaximumOfAnEmptyTreeShouldBeNull() {
        assertNull(tree.max());
    }

    @Test
    public void theMaximumOfATreeWithOneValueShouldBeThatValue() {
        tree.add(4892);
        assertEquals((Integer) 4892, tree.max());
    }

    @Test
    public void theMaximumOfATreeWithSeveralValuesShouldBeTheMaximumValue() {
        tree.add(1489);
        tree.add(192321);

        assertEquals((Integer) 192321, tree.max());
    }

    @Test
    public void theMaximumOfATreeWithVeryManyValuesShouldStillBeTheMaximumValue() {
        final int minValue = 345;
        final int maxValue = 9231;

        for (int i = minValue; i <= maxValue; i++) {
            tree.add(i);
        }

        assertEquals((Integer) maxValue, tree.max());
    }
}