import net.miladinov.avltree.AvlTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static net.miladinov.util.MethodNamesAsFailureMessages.*;

public class AsCollectionTests {
    private Collection<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
        junit.framework.Assert.assertTrue("Every test should begin with an empty tree", tree.isEmpty());
    }

    @Test
    public void addingANewElementToAnEmptyCollectionShouldAlwaysModifyTheTree() {
        assertTrue(tree.add(127));
    }

    @Test
    public void addingAnElementAlreadyContainedByTheCollectionShouldNeverModifyTheTree() {
        tree.add(47);
        assertFalse(tree.add(47));
    }

    @Test
    public void passingAnEmptyCollectionToAddAllShouldNeverModifyTheTree() {
        final Collection<Integer> emptyList = new LinkedList<Integer>();
        assertFalse(tree.addAll(emptyList));
    }

    @Test
    public void callsToAddAllShouldReturnTrueIfTheyShouldModifyTheTree() {
        final Collection<Integer> list = new LinkedList<Integer>();
        list.add(7);
        assertTrue(tree.addAll(list));
    }

    @Test
    public void allOfTheElementsInTheCollectionShouldBeAddedToTheTreeWhenPassedToAddAll() {
        final Collection<Integer> list = new LinkedList<Integer>();
        list.add(3);
        list.add(4);
        list.add(5);

        tree.addAll(list);
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(5));

    }

    @Test
    public void aNonEmptyTreeShouldBecomeEmptyAfterCallingClearOnIt() {
        tree.add(4);
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    public void containsShouldReturnFalseWhenGivenAnElementTheTreeDoesNotContain() {
        assertFalse(tree.contains(38));
    }

    @Test
    public void containsShouldReturnTrueWhenGivenAnElementTheTreeContains() {
        tree.add(27);
        assertTrue(tree.contains(27));
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Test
    public void containsShouldThrowAClassCastExceptionWhenGivenAnElementIncompatibleWithItsGenericTypeAndTheTreeContainsElements() {
        tree.add(154);
        try {
            assertFalse(tree.contains("Not An Integer"));
            fail();
        } catch (ClassCastException cce) {
        }
    }

    @Test
    public void containsAllShouldReturnTrueWhenPassedAnEmptyCollectionAndTheTreeIsEmpty() {
        assertTrue(tree.containsAll(new LinkedList<Integer>()));
    }

    @Test
    public void containsAllShouldReturnTrueWhenPassedAnEmptyCollectionAndTheTreeIsNotEmpty() {
        tree.add(165);
        assertTrue(tree.containsAll(new LinkedList<Integer>()));
    }

    @Test
    public void containsAllShouldReturnFalseWhenPassedACollectionThatContainsMoreElementsThanTheTreeDoes() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(15);
        list.add(16);
        list.add(17);

        tree.add(15);
        tree.add(16);

        assertFalse(tree.containsAll(list));
    }

    @Test
    public void containsAllShouldReturnTrueWhenPassedACollectionThatContainsAllTheSameElementsThatTheTreeDoes() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(156);
        list.add(3);
        list.add(271);
        list.add(38);

        tree.add(156);
        tree.add(3);
        tree.add(271);
        tree.add(38);

        assertTrue(tree.containsAll(list));
    }

    @Test
    public void containsAllShouldReturnTrueWhenPassedACollectionThatContainsLessElementsThanTheTreeDoesButTheTreeContainsAllTheElementsThatTheCollectionDoes() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(17);
        list.add(22);
        list.add(92);

        tree.add(17);
        tree.add(22);
        tree.add(92);
        tree.add(28);
        tree.add(12);

        assertTrue(tree.containsAll(list));
    }

    @Test
    public void isEmptyShouldReturnTrueWhenTheTreeIsEmpty() {
        assertTrue(tree.isEmpty());
    }

    @Test
    public void isEmptyShouldReturnFalseWhenTheTreeIsNotEmpty() {
        tree.add(0);
        assertFalse(tree.isEmpty());
    }

    @Test
    public void theIteratorReturnedFromTheTreeShouldNeverBeNull() {
        assertNotNull(tree.iterator());
    }

    @Test
    public void theIteratorReturnedFromTheTreeShouldIterateOverAllTheElementsTheTreeContains() {
        tree.add(48);
        tree.add(182);
        tree.add(98);
        tree.add(62);
        tree.add(39);

        List<Integer> list = ((AvlTree<Integer>) tree).asInorderList();

        for (Integer aTree : tree) {
            list.remove(aTree);
        }

        assertTrue(list.isEmpty());
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Test
    public void removeShouldThrowAClassCastExceptionWhenTryingToRemoveAnElementIncompatibleWithItsGenericTypeAndTheTreeContainsElements() {
        tree.add(27);
        try {
            tree.remove("Not an Integer");
            fail();
        } catch (ClassCastException cce) {
        }
    }

    @Test
    public void removeShouldReturnFalseWhenItDidNotModifyTheTreeAsWhenRemovingAnElementItDidNotContain() {
        assertFalse(tree.remove(22));
    }

    @Test
    public void removeShouldReturnTrueWhenItModifiedTheTreeAsWhenRemovingAnElementItContained() {
        tree.add(3);
        assertTrue(tree.remove(3));
    }

    @Test
    public void removeAllShouldAlwaysReturnFalseWhenPassedAnEmptyCollection() {
        final Collection<Integer> collection = new LinkedList<Integer>();
        assertFalse(tree.removeAll(collection));
    }

    @Test
    public void removeAllShouldReturnTrueWhenAtLeastOneElementInTheCollectionPassedToItExistedInTheTree() {
        final Collection<Integer> collection = new LinkedList<Integer>();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        tree.add(3);
        tree.add(4);
        tree.add(5);

        assertTrue(tree.removeAll(collection));
    }

    @Test
    public void removeAllShouldClearOutTheTreeWhenPassedACollectionThatContainsAllOfTheElementsThatWereInTheTree() {
        final Collection<Integer> collection = new LinkedList<Integer>();
        collection.add(1);
        collection.add(2);
        collection.add(3);

        tree.add(2);
        tree.add(3);

        tree.removeAll(collection);
        assertTrue(tree.isEmpty());
    }

    @Test
    public void retainAllShouldReturnFalseWhenPassingACollectionToAnEmptyTree() {
        final Collection<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertFalse(tree.retainAll(list));
    }

    @Test
    public void retainAllShouldEmptyATreeIfItContainedNotASingleElementContainedInTheCollectionPassedToIt() {
        final Collection<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        tree.add(8);
        tree.add(9);
        tree.add(10);

        tree.retainAll(list);
        assertTrue(tree.isEmpty());
    }

    @Test
    public void retainAllShouldReturnTrueIfItCausesEvenOneElementToBeRemovedFromTheTree() {
        final Collection<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);

        assertTrue(tree.retainAll(list));
    }

    @Test
    public void retainAllShouldLeaveTheTreeContainingOnlyTheElementsThatWereInTheCollectionPassedToIt() {
        final Collection<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        tree.add(3);
        tree.add(4);
        tree.add(5);

        tree.retainAll(list);

        assertTrue(tree.contains(3));
        assertFalse(tree.contains(4));
        assertFalse(tree.contains(5));
        assertFalse(tree.contains(1));
        assertFalse(tree.contains(2));
    }

    @Test
    public void toArrayShouldReturnTheTreeRepresentedAsASortedArray() {
        tree.add(1);
        tree.add(5);
        tree.add(3);
        tree.add(2);
        tree.add(4);

        final Object[] expected = {1, 2, 3, 4, 5,};
        final Object[] actual = tree.toArray();
        assertEquals(expected.length, actual.length);

        for (int index = 0; index < expected.length; index++) {
            assertEquals(expected[index], actual[index]);
        }
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    @Test
    public void toParameterizedArrayShouldReturnTheTreeRepresentedAsASortedArray() {
        tree.add(6);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(4);
        tree.add(2);

        final Integer[] expected = {1, 2, 3, 4, 5, 6,};
        final Integer[] actual = tree.toArray(new Integer[0]);
        assertEquals(expected.length, actual.length);

        for (int index = 0; index < expected.length; index++) {
            assertEquals(expected[index], actual[index]);
        }
    }

    @SuppressWarnings("ToArrayCallWithZeroLengthArrayArgument")
    @Test(expected = ArrayStoreException.class)
    public void toParameterizedArrayShouldThrowAnArrayStoreExceptionIfTheRuntimeTypeOfTheSpecifiedArrayIsNotASupertypeOfTheRuntimeTypeOfEveryElementInTheCollection() {
        tree.add(1);
        tree.add(2);
        tree.add(3);

        tree.toArray(new String[0]);
    }

    @Test(expected = NullPointerException.class)
    public void toParameterizedArrayShouldThrowANullPointerExceptionIfTheSpecifiedArrayIsNull() {
        tree.add(1);
        tree.add(2);
        tree.add(3);

        tree.toArray(null);
    }
}