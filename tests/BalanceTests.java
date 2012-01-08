import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BalanceTests {
    private AvlTree<Integer> tree;

    @Before
    public void before() {
        tree = new AvlTree<Integer>();
    }

    @Test
    public void heavyOnTheRightWithARightHeavySubtreeGetsRotatedLeft() {
        tree.add(1);
        tree.add(2);
        tree.add(3);

        assertEquals(Arrays.asList(1, 3, 2), tree.asPostorderList());
    }

    @Test
    public void heavyOnTheLeftWithALeftHeavySubtreeGetsRotatedRight() {
        tree.add(3);
        tree.add(2);
        tree.add(1);

        assertEquals(Arrays.asList(1, 3, 2), tree.asPostorderList());
    }

    @Test
    public void heavyOnTheRightWithALeftHeavySubtreeGetsRotatedLeft() {
        tree.add(7);
        tree.add(9);
        tree.add(8);

        assertEquals(Arrays.asList(7, 9, 8), tree.asPostorderList());
    }

    @Test
    public void heavyOnTheLEftWithARightHeavySubtreeGetsRotatedRight() {
        tree.add(9);
        tree.add(7);
        tree.add(8);

        assertEquals(Arrays.asList(7, 9, 8), tree.asPostorderList());
    }
}
