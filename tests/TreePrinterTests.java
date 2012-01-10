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
        assertEquals(
            " - 2 - 3\n" +
                "     \\ 1\n",
            TreePrinter.print(tree)
        );
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

        assertEquals(
            " -  7 -  9 - 10\n" +
                "           \\  8\n" +
                "      \\  2 -  4\n" +
                "           \\  1\n",
            TreePrinter.print(tree)
        );
    }

    @Test
    public void printingABigTree() {
        for (int i = 1; i <= 50; i++) {
            tree.add(i);
        }
        assertEquals(
            " - 32 - 40 - 44 - 48 - 49 - 50\n" +
                "                     \\ 46 - 47\n" +
                "                          \\ 45\n" +
                "                \\ 42 - 43\n" +
                "                     \\ 41\n" +
                "           \\ 36 - 38 - 39\n" +
                "                     \\ 37\n" +
                "                \\ 34 - 35\n" +
                "                     \\ 33\n" +
                "      \\ 16 - 24 - 28 - 30 - 31\n" +
                "                          \\ 29\n" +
                "                     \\ 26 - 27\n" +
                "                          \\ 25\n" +
                "                \\ 20 - 22 - 23\n" +
                "                          \\ 21\n" +
                "                     \\ 18 - 19\n" +
                "                          \\ 17\n" +
                "           \\  8 - 12 - 14 - 15\n" +
                "                          \\ 13\n" +
                "                     \\ 10 - 11\n" +
                "                          \\  9\n" +
                "                \\  4 -  6 -  7\n" +
                "                          \\  5\n" +
                "                     \\  2 -  3\n" +
                "                          \\  1\n",
            TreePrinter.print(tree)
        );
    }

    @Test
    public void printingAnEvenBiggerTree() {
        for (int i = 100; i > 0; i--) {
            tree.add(i);
        }
        assertEquals(
            " -  37 -  69 -  85 -  93 -  97 -  99 - 100\n" +
                "                                     \\  98\n" +
                "                               \\  95 -  96\n" +
                "                                     \\  94\n" +
                "                         \\  89 -  91 -  92\n" +
                "                                     \\  90\n" +
                "                               \\  87 -  88\n" +
                "                                     \\  86\n" +
                "                   \\  77 -  81 -  83 -  84\n" +
                "                                     \\  82\n" +
                "                               \\  79 -  80\n" +
                "                                     \\  78\n" +
                "                         \\  73 -  75 -  76\n" +
                "                                     \\  74\n" +
                "                               \\  71 -  72\n" +
                "                                     \\  70\n" +
                "             \\  53 -  61 -  65 -  67 -  68\n" +
                "                                     \\  66\n" +
                "                               \\  63 -  64\n" +
                "                                     \\  62\n" +
                "                         \\  57 -  59 -  60\n" +
                "                                     \\  58\n" +
                "                               \\  55 -  56\n" +
                "                                     \\  54\n" +
                "                   \\  45 -  49 -  51 -  52\n" +
                "                                     \\  50\n" +
                "                               \\  47 -  48\n" +
                "                                     \\  46\n" +
                "                         \\  41 -  43 -  44\n" +
                "                                     \\  42\n" +
                "                               \\  39 -  40\n" +
                "                                     \\  38\n" +
                "       \\  21 -  29 -  33 -  35 -  36\n" +
                "                               \\  34\n" +
                "                         \\  31 -  32\n" +
                "                               \\  30\n" +
                "                   \\  25 -  27 -  28\n" +
                "                               \\  26\n" +
                "                         \\  23 -  24\n" +
                "                               \\  22\n" +
                "             \\  13 -  17 -  19 -  20\n" +
                "                               \\  18\n" +
                "                         \\  15 -  16\n" +
                "                               \\  14\n" +
                "                   \\   5 -   9 -  11 -  12\n" +
                "                                     \\  10\n" +
                "                               \\   7 -   8\n" +
                "                                     \\   6\n" +
                "                         \\   3 -   4\n" +
                "                               \\   2\n" +
                "                                     \\   1\n",
            TreePrinter.print(tree));
    }
}
