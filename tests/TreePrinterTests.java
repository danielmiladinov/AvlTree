import net.miladinov.avltree.AvlTree;
import net.miladinov.avltree.TreePrinter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreePrinterTests {
    private AvlTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new AvlTree<Integer>();
    }

    @Test
    public void emptyTree() {
        assertEquals("<empty tree>", TreePrinter.print(tree));
    }

    @Test
    public void oneElement() {
        tree.add(5);
        assertEquals(" - 5\n", TreePrinter.print(tree));
    }

    @Test
    public void twoElementsBothOnRight() {
        tree.add(7);
        tree.add(9);
        assertEquals(" - 7 - 9\n", TreePrinter.print(tree));
    }

    @Test
    public void oneRightAndOneLeftOfRoot() {
        tree.add(2);
        tree.add(3);
        tree.add(1);
        assertEquals(" - 2 - 3\n     \\ 1\n", TreePrinter.print(tree));
    }

    @Test
    public void aMoreComplexExample() {
        tree.add(7);
        tree.add(2);
        tree.add(10);
        tree.add(1);
        tree.add(4);
        tree.add(8);
        tree.add(9);

        assertEquals(" -  7 -  9 - 10\n           \\  8\n      \\  2 -  4\n           \\  1\n", TreePrinter.print(tree));
    }

    @Test
    public void junk() {

        for (int i = 1; i < 31; i++) {
            tree.add(i);
            System.out.println();
            System.out.println(String.format("Printing after adding %s", i));
            System.out.println(TreePrinter.print(tree));
        }
    }

    @Test
    public void moreJunk() {
        for (int i = 100; i > 0; i--) {
            tree.add(i);
            System.out.println();
            System.out.println(String.format("Printing after adding %s", i));
            System.out.println(TreePrinter.print(tree));
        }
    }
}
