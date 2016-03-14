package csci242.assignments.stringhandler;

import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Predicate;

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
public class HexStringHandlerTest {

    HexStringHandler hexHandler;


    @Before
    public void setUp() throws Exception {
        hexHandler = new HexStringHandler();
    }


    @Test
    public void testIsValidHexLetter() throws Exception {
        for(char i = 0; i < 256; i++) {
            if(i < 'A' || (i > 'F' && i < 'a') || i > 'f') {
                assertFalse(hexHandler.isValidHexLetter(i));
            } else {
                assertTrue(hexHandler.isValidHexLetter(i));
            }
        }
    }

    @Test
    public void testProcessDigit() throws Exception {
        loopTest(Character::isDigit, hexHandler::processDigit, "processDigit");
    }

    @Test
    public void testProcessLetter() throws Exception {
        loopTest(hexHandler::isValidHexLetter, hexHandler::processLetter,
                "processLetter");
    }

    @Test
    public void testProcessOther() throws Exception {
        loopTest((c) ->
                    !(hexHandler.isValidHexLetter(c) || Character.isDigit(c)),
                hexHandler::processOther, "processOther");
    }


    private void loopTest(Predicate<Character> validator,
                          Consumer<Character> processor, String processorName)
            throws Exception {

        for(char i = 0; i < 256; i++) {
            boolean valid = validator.test(i);

            try {
                processor.accept(i);
            } catch(IllegalArgumentException e) {
                if(valid) {
                    // Exception incorrectly thrown.
                    fail(processorName + " threw incorrectly at: " + i);
                }
                // Exception correctly thrown, continue.
                continue;
            }

            if(!valid) {
                // Exception should have been thrown.
                fail(processorName + " should have thrown at: " + i);
            }
        }
    }
}
