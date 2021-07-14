package training.java.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageUtilsUnitTest {
    @Test public void testGetMessage() {
        assertEquals("Hello      World!", MessageUtils.getMessage());
    }
}
