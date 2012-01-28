import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static net.miladinov.util.MethodNamesAsFailureMessages.assertFalse;
import static net.miladinov.util.MethodNamesAsFailureMessages.assertTrue;

public class AsCollectionTests {
    private Collection<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
        junit.framework.Assert.assertTrue("Every test should begin with an empty tree", tree.isEmpty());
    }

    @Test
    public void addingANewElementToAnEmptyCollectionShouldAlwaysModifyTheCollection() {
        assertTrue(tree.add(127));
    }

    @Test
    public void addingAnElementAlreadyContainedByTheCollectionShouldNeverModifyTheCollection() {
        tree.add(47);
        assertFalse(tree.add(47));
    }

    @Test
    public void passingAnEmptyCollectionToAddAllShouldNeverModifyTheCollection() {
        Collection<Integer> emptyList = new LinkedList<Integer>();
        assertFalse(tree.addAll(emptyList));
    }

    @Test
    public void callsToAddAllShouldReturnTrueIfTheyShouldModifyTheCollection() {
        Collection<Integer> list = new LinkedList<Integer>();
        list.add(7);
        assertTrue(tree.addAll(list));
    }
}