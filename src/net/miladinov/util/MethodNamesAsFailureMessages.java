package net.miladinov.util;

import static net.miladinov.util.StringConverter.callingMethodNameAsSentence;

public class MethodNamesAsFailureMessages {
    public static void assertTrue(boolean condition) {
        junit.framework.Assert.assertTrue(callingMethodNameAsSentence(), condition);
    }

    public static void assertFalse(boolean condition) {
        junit.framework.Assert.assertFalse(callingMethodNameAsSentence(), condition);
    }

    public static void fail() {
        junit.framework.Assert.fail(callingMethodNameAsSentence());
    }

    public static void assertEquals(Object expected, Object actual) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual);
    }

    public static void assertEquals(double expected, double actual, double delta) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual, delta);
    }

    public static void assertEquals(float expected,float actual, float delta) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual, delta);
    }

    public static void assertEquals(long expected, long actual) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual);
    }

    public static void assertEquals(boolean expected, boolean actual) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual);
    }

    public static void assertEquals(byte expected, byte actual) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual);
    }

    public static void assertEquals(char expected, char actual) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual);
    }

    public static void assertEquals(short expected, short actual) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual);
    }

    public static void assertEquals(int expected, int actual) {
        junit.framework.Assert.assertEquals(callingMethodNameAsSentence(), expected, actual);
    }

    public static void assertNotNull(Object object) {
        junit.framework.Assert.assertNotNull(callingMethodNameAsSentence(), object);
    }

    public static void assertNull(Object object) {
        junit.framework.Assert.assertNull(callingMethodNameAsSentence(), object);
    }

   public static void assertSame(Object expected, Object actual) {
       junit.framework.Assert.assertSame(callingMethodNameAsSentence(), expected, actual);
   }

    public static void assertNotSame(Object expected, Object actual) {
        junit.framework.Assert.assertNotSame(callingMethodNameAsSentence(), expected, actual);
    }

    public static void failSame() {
        junit.framework.Assert.failSame(callingMethodNameAsSentence());
    }

    public static void failNotSame(Object expected, Object actual) {
        junit.framework.Assert.assertNotSame(callingMethodNameAsSentence(), expected, actual);
    }

    public static void failNotEquals(Object expected, Object actual) {
        junit.framework.Assert.failNotEquals(callingMethodNameAsSentence(), expected, actual);
    }
}
