import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

public class TraversalTests {

    private AvlTree<Integer> tree;

    @Before
    public void setUp() {
        this.tree = new AvlTree<Integer>();
    }

    @Test
    public void thePreorderTraversalListShouldRevealTreeStructure() {
        tree.add(7);
        tree.add(4);
        tree.add(10);

        assertEquals(Arrays.asList(7, 4, 10), tree.asPreorderList());
    }

    @Test
    public void biggerTreesShouldBeJustAsEasyToTraverse() {
        tree.add(7);
        tree.add(4);
        tree.add(10);
        tree.add(9);
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(5);
        tree.add(6);
        tree.add(8);

        assertEquals(Arrays.asList(7, 4, 2, 1, 3, 5, 6, 9, 8, 10), tree.asPreorderList());
    }

    @Test
    public void treeValuesShouldBeInSortedAscendingOrderWhenReturnedAsInorderList() {
        tree.add(5);
        tree.add(3);
        tree.add(9);
        tree.add(4);
        tree.add(8);
        tree.add(1);
        tree.add(2);
        tree.add(6);
        tree.add(7);
        tree.add(10);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), tree.asInorderList());
    }

    @Test
    public void treeValuesShouldBeInTheExpectedOrderWhenReturnedAsPostorderList() {
        tree.add(50);
        tree.add(25);
        tree.add(75);
        tree.add(10);
        tree.add(30);
        tree.add(27);
        tree.add(45);
        tree.add(90);
        tree.add(80);

        assertEquals(Arrays.asList(10, 27, 45, 30, 25, 75, 90, 80, 50), tree.asPostorderList());
    }
}
