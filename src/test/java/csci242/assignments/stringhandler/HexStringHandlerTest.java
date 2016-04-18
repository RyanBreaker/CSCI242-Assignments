package csci242.assignments.stringhandler;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Implement a string parsing system, called StringHandler, in Java that uses
 * interfaces to specify common behavior and interface implementations to
 * specify specific behavior.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 3
 * @bugs None
 */
public class HexStringHandlerTest {

    HexStringHandler hexHandler;


    @Before
    public void setUp() throws Exception {
        // Reset hexHandler for each test.
        hexHandler = new HexStringHandler();

        // Test initial values.
        assertTrue(hexHandler.isValid());
        assertEquals(0, hexHandler.getNumber());
    }


    @Test
    public void testIsValidHexLetter() throws Exception {
        for (char i = 0; i < 256; i++) {
            if (i < 'A' || (i > 'F' && i < 'a') || i > 'f') {
                assertFalse(hexHandler.isValidHexLetter(i));
            } else {
                assertTrue(hexHandler.isValidHexLetter(i));
            }
        }
    }

    @Test
    public void testProcessDigit() throws Exception {
        StringHandlerTestHelper.loopTest(Character::isDigit, hexHandler::processDigit, "processDigit");
    }

    @Test
    public void testProcessLetter() throws Exception {
        StringHandlerTestHelper.loopTest(Character::isAlphabetic, hexHandler::processLetter,
                "processLetter");
    }

    @Test
    public void testProcessOther() throws Exception {
        StringHandlerTestHelper.loopTest((c) ->
                        !(hexHandler.isValidHexLetter(c) || Character.isDigit(c)),
                hexHandler::processOther, "processOther");
    }

    @Test
    public void testIsValidTrue() throws Exception {
        StringParser parser = new StringParser(hexHandler);
        int number = 0;

        for (int i = 0; i < HexStringHandler.NUMBER_SYSTEM; i++) {
            number += i;
        }

        parser.parse("1234567890ABCDEF");

        assertTrue(hexHandler.isValid());
        assertEquals(number, hexHandler.getNumber());
    }

    @Test
    public void testIsValidFalse() throws Exception {
        StringParser parser = new StringParser(hexHandler);

        try {
            parser.parse("ABCDEFGHIJKLMNOP");
        } catch (IllegalArgumentException e) {
            assertEquals(HexStringHandler.INVALIDHEX_ERROR, e.getMessage());
        }

        assertFalse(hexHandler.isValid());
        assertEquals(HexStringHandler.INVALID_NUMBER, hexHandler.getNumber());
    }
}
