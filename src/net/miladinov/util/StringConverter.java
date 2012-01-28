package net.miladinov.util;

public class StringConverter {

    public static String currentMethodNameAsSentence() {
        final StackTraceElement callingMethodStackFrame = Thread.currentThread().getStackTrace()[2];
        return camelCaseToSentence(callingMethodStackFrame.getMethodName());
    }

    public static String camelCaseToSentence(String camelCased) {
        StringBuilder sentence = new StringBuilder();
        sentence.append(camelCased.substring(0, 1).toUpperCase());
        sentence.append(camelCased.substring(1, camelCased.length()).replaceAll("([A-Z])", " $1").toLowerCase());
        return sentence.toString();
    }
}
