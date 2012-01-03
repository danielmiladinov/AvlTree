import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.*;

public class AvlTreeTests {
    private AvlTree<Integer> tree;

    @Before
    public void setUp() {
        this.tree = new AvlTree<Integer>();
    }

    @Test
    public void anEmptyTreeShouldHaveASizeOfZero() {
        assertEquals(0, tree.size());
    }

    @Test
    public void anEmptyTreeShouldReportItselfAsSuch() {
        assertTrue(tree.isEmpty());
    }

    @Test
    public void afterAddingAnElementTheTreeShouldHaveASizeOfOne() {
        tree.add(5);
        assertEquals(1, tree.size());
    }

    @Test
    public void afterAddingASecondElementTheTreeShouldHaveASizeOfTwo() {
        tree.add(5);
        tree.add(7);
        assertEquals(2, tree.size());
    }

    @Test
    public void afterAddingAnElementTheTreeShouldNoLongerBeEmpty() {
        tree.add(7);
        assertFalse(tree.isEmpty());
    }

    @Test
    public void beforeAddingAnElementTheTreeShouldNotContainAnElement() {
        assertFalse(tree.contains(10));
    }

    @Test
    public void afterAddingAnElementTheTreeShouldContainThatElement() {
        tree.add(4);
        assertTrue(tree.contains(4));
    }

    @Test
    public void afterAddingASecondElementTheTreeShouldContainThatElementAlso() {
        tree.add(4);
        tree.add(7);
        assertTrue(tree.contains(7));
    }

    @Test
    public void afterAddingASecondElementTheTreeShouldStillContainTheFirstElement() {
        tree.add(4);
        tree.add(7);
        assertTrue(tree.contains(4));
    }

    @Test
    public void addReturnsTrueWhenItChangesTheStructureOfTheTreeWhenItWasEmpty() {
        assertTrue(tree.add(4));
    }

    @Test
    public void addReturnsTrueWhenItChangesTheStructureOfTheTreeWhenItWasNotEmpty() {
        tree.add(4);
        assertTrue(tree.add(8));
    }

    @Test
    public void addReturnsFalseWhenItDoesNotChangeTheStructureOfTheTreeWhenItWasNotEmpty() {
        tree.add(4);
        assertFalse(tree.add(4));
    }

    @Test
    public void removingAValueFromTheTreeThatItDidNotContainReturnsFalse() {
        assertFalse(tree.remove(6));
    }

    @Test
    public void removingAValueFromTheTreeThatItContainedReturnsTrue() {
        tree.add(3);
        assertTrue(tree.remove(3));
    }

    @Test
    public void theTreeNoLongerContainsTheValueThatWasRemovedFromIt() {
        tree.add(10);
        tree.add(4);
        tree.add(1);
        tree.add(2);
        tree.remove(1);
        assertFalse(tree.contains(1));
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

        assertEquals(Arrays.asList(7, 4, 1, 2, 3, 5, 6, 10, 9, 8), tree.asPreorderList());
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

        assertEquals(Arrays.asList(10, 27, 45, 30, 25, 80, 90, 75, 50), tree.asPostorderList());
    }
}
