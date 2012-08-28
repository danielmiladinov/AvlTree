import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AsCollectionTests {
    private Collection<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
        assertTrue("Every test should begin with an empty tree", tree.isEmpty());
    }

    @Test
    public void addingAnElementToAnEmptyCollectionShouldReturnTrue() {
        assertTrue("Adding a new element to an empty collection should always modify the collection", tree.add(127));
    }

    @Test
    public void addingAnElementThatTheCollectionAlreadyContainsShouldReturnFalse() {
        tree.add(47);
        assertFalse("Adding an element already contained by the collection should never modify the collection", tree.add(47));
    }

    @Test
    public void passingAnEmptyCollectionToAddAllShouldReturnFalse() {
        Collection<Integer> emptyList = new LinkedList<Integer>();
        assertFalse("Passing an empty collection to addAll should never modify the collection", tree.addAll(emptyList));
    }

    @Test
    public void passingANonEmptyCollectionToAddAllShouldReturnTrueIfItAddsAtLeastOneNewElement() {
        Collection<Integer> list = new LinkedList<Integer>();
        list.add(7);
        assertTrue("Calls to addAll should return true if they should modify the collection", tree.addAll(list));
    }
}