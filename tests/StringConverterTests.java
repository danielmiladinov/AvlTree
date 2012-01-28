import net.miladinov.util.StringConverter;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StringConverterTests {
    @Test
    public void thisMethodNameShouldBeTurnedIntoASentence() {
        assertEquals("This method name should be turned into a sentence", StringConverter.currentMethodNameAsSentence());
    }

    @Test
    public void thisMethodNameShouldAlsoBeTurnedIntoASentence() {
        assertEquals("This method name should also be turned into a sentence", StringConverter.currentMethodNameAsSentence());
    }

    @Test
    public void shouldBeAbleToConvertACamelCasedMethodNameToASentence() {
        assertEquals(
            "Should be able to convert a camel cased method name to a sentence",
            StringConverter.camelCaseToSentence("shouldBeAbleToConvertACamelCasedMethodNameToASentence")
        );
    }
}
