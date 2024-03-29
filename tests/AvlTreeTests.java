import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

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
    public void removingAValueFromTheTreeDecrementsItsSize() {
        tree.add(4);
        tree.remove(4);
        assertEquals(0, tree.size());
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


}
