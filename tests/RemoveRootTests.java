import net.miladinov.avltree.AvlTree;
import net.miladinov.avltree.TreePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RemoveRootTests {
    private AvlTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
    }

    @After
    public void tearDown() {
        System.out.println(TreePrinter.print(tree));
    }

    @Test
    public void removingTheRootFromALeftHeavyTreePromotesItsLeftChildToBecomeTheNewRoot() {
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(1);

        tree.remove(3);
    }
}