package csci242.assignments.stringhandler;

import org.junit.Before;
import org.junit.Test;

import static csci242.assignments.stringhandler.StringHandlerTest.*;
import static org.junit.Assert.*;

/**
 * Short description.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 3
 * @bugs None
 */
public class PasswordSecurityHandlerTest {

    PasswordSecurityHandler passwordHandler;


    @Before
    public void setUp() throws Exception {
        passwordHandler = new PasswordSecurityHandler();
    }


    @Test
    public void testProcessDigit() throws Exception {
        int length;
        char start = '0', end = '9';

        assertFalse(passwordHandler.getDigit());
        assertFalse(passwordHandler.getOtherCharacter());

        length = loopTest(start, end, passwordHandler::processDigit);

        assertEquals(length, passwordHandler.getLength());
        assertTrue(passwordHandler.getDigit());
        assertFalse(passwordHandler.getOtherCharacter());


        // Test Exception throwing
        try {
            passwordHandler.processDigit('A');
            // If we get here, the test failed.
            fail("processDigit failed Exception test.");
        } catch (IllegalArgumentException e) {
            assertEquals(StringHandler.PROCESSDIGIT_ERROR, e.getMessage());
        }
    }


    @Test
    public void testProcessLetter() throws Exception {
        int length;
        char start = 'A', end = 'z';

        length = loopTest(start, end, passwordHandler::processLetter);

        assertEquals(length, passwordHandler.getLength());
        assertFalse(passwordHandler.getDigit());
        assertFalse(passwordHandler.getOtherCharacter());

        // Test Exception throwing
        try {
            passwordHandler.processLetter('0');
            // If we get here, the test failed.
            fail("processLetter failed Exception test.");
        } catch (IllegalArgumentException e) {
            assertEquals(StringHandler.PROCESSLETTER_ERROR, e.getMessage());
        }
    }


    @Test
    public void testProcessOther() throws Exception {
        int length = 0;
        char start = (char)0, end = (char)255;

        length += loopTest(start, (char)('0'-1),
                passwordHandler::processOther);
        length += loopTest((char)('9'+1), (char)('A'-1),
                passwordHandler::processOther);
        length += loopTest((char)('z'+1), end,
                passwordHandler::processOther);

        assertEquals(length, passwordHandler.getLength());

        // Test Exception throwing
        try {
            passwordHandler.processOther('A');
            // If we get here, the test failed.
            fail("processOther failed Exception test.");
        } catch (IllegalArgumentException e) {
            assertEquals(StringHandler.PROCESSOTHER_ERROR, e.getMessage());
        }
    }
}
