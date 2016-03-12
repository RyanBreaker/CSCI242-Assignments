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
public class PasswordSecurityHandlerTests {

    PasswordSecurityHandler passwordHandler;

    @Before
    public void setUp() throws Exception {
        // Reset passwordHandler for each test.
        passwordHandler = new PasswordSecurityHandler();

        assertFalse(passwordHandler.getDigit());
        assertFalse(passwordHandler.getOtherCharacter());
    }


    //region Security tests
    @Test
    public void testSecurityLevelWeak() throws Exception {
        String password = "foobar";
        StringParser parser = new StringParser(passwordHandler);

        parser.parse(password);

        assertEquals(password.length(), passwordHandler.getLength());
        assertEquals(PasswordSecurityHandler.SECURITY_LEVEL_WEAK,
                passwordHandler.securityLevel());

        assertFalse(passwordHandler.getDigit());
        assertFalse(passwordHandler.getOtherCharacter());
    }

    @Test
    public void testSecurityMediumDigit() throws Exception {
        String password = "foobar123";
        StringParser parser = new StringParser(passwordHandler);

        parser.parse(password);

        assertEquals(password.length(), passwordHandler.getLength());
        assertEquals(PasswordSecurityHandler.SECURITY_LEVEL_MEDIUM,
                passwordHandler.securityLevel());

        assertTrue(passwordHandler.getDigit());
        assertFalse(passwordHandler.getOtherCharacter());
    }

    @Test
    public void testSecurityMediumOther() throws Exception {
        String password = "foobar!@#";
        StringParser parser = new StringParser(passwordHandler);

        parser.parse(password);

        assertEquals(password.length(), passwordHandler.getLength());
        assertEquals(PasswordSecurityHandler.SECURITY_LEVEL_MEDIUM,
                passwordHandler.securityLevel());

        assertFalse(passwordHandler.getDigit());
        assertTrue(passwordHandler.getOtherCharacter());
    }

    @Test
    public void testSecurityStrong() throws Exception {
        String password = "foobar123!@#";
        StringParser parser = new StringParser(passwordHandler);

        parser.parse(password);

        assertEquals(password.length(), passwordHandler.getLength());
        assertEquals(PasswordSecurityHandler.SECURITY_LEVEL_STRONG,
                passwordHandler.securityLevel());

        assertTrue(passwordHandler.getDigit());
        assertTrue(passwordHandler.getOtherCharacter());
    }
    //endregion


    @Test
    public void testProcessDigit() throws Exception {
        int length = loopTest(CharType.Digit, passwordHandler::processDigit);

        assertEquals(length, passwordHandler.getLength());
        assertTrue(passwordHandler.getDigit());
        assertFalse(passwordHandler.getOtherCharacter());
    }


    @Test
    public void testProcessLetter() throws Exception {
        int length = loopTest(CharType.Letter, passwordHandler::processLetter);

        assertEquals(length, passwordHandler.getLength());
        assertFalse(passwordHandler.getDigit());
        assertFalse(passwordHandler.getOtherCharacter());
    }


    @Test
    public void testProcessOther() throws Exception {
        int length = loopTest(CharType.Other, passwordHandler::processOther);

        assertEquals(length, passwordHandler.getLength());
        assertFalse(passwordHandler.getDigit());
        assertTrue(passwordHandler.getOtherCharacter());
    }
}