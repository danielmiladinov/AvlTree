import junit.framework.AssertionFailedError;
import net.miladinov.util.MethodNamesAsFailureMessages;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class MethodNamesAsFailureMessagesTests {

    @Test
    public void assertTrueUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertTrue(false);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert true uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertFalseUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertFalse(true);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert false uses the test method name for its failure message"));
        }
    }

    @Test
    public void failUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.fail();
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Fail uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForObjectsUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals(new Object(), new Object());
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for objects uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForStringsUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals("expected", "actual");
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for strings uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForDoublesUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals(1.0d, 2.4d, 0.1d);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for doubles uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForFloatsUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals(1.2f, 3.4f, 0.1f);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for floats uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForLongsUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals(1L, 2L);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for longs uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForBooleansUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals(true, false);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for booleans uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForBytesUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals((byte)0, (byte)1);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for bytes uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForCharactersUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals('a', 'b');
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for characters uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForShortsUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals((short)1, (short)0);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for shorts uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertEqualsForIntegersUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertEquals(1, 0);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert equals for integers uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertNotNullUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertNotNull(null);
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert not null uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertNullUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertNull("I'm not null");
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert null uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertSameUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertSame("same", "not same");
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert same uses the test method name for its failure message"));
        }
    }

    @Test
    public void assertNotSameUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.assertNotSame("same", "same");
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Assert not same uses the test method name for its failure message"));
        }
    }

    @Test
    public void failSameUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.failSame();
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Fail same uses the test method name for its failure message"));
        }
    }

    @Test
    public void failNotSameUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.failNotSame("same", "same");
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Fail not same uses the test method name for its failure message"));
        }
    }

    @Test
    public void failNotEqualsUsesTheTestMethodNameForItsFailureMessage() {
        try {
            MethodNamesAsFailureMessages.failNotEquals("3", "3");
            fail("The method was supposed to throw an AssertionFailedError, but didn't");
        } catch (AssertionFailedError afe) {
            assertTrue(afe.getMessage().startsWith("Fail not equals uses the test method name for its failure message"));
        }
    }
}
