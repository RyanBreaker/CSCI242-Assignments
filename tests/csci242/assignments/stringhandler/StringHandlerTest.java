package csci242.assignments.stringhandler;

import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.fail;

/**
 * Short description.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 2
 * @bugs None
 */
public class StringHandlerTest {

    private StringHandlerTest() {}

    protected enum CharType {
        Digit, Letter, Other
    }

    private static Function<Character, Boolean> checker;

    private static boolean isOther(char c) {
        return !(Character.isDigit(c) || Character.isAlphabetic(c));
    }

    private static String message(String method, char c) {
        return String.format("Method %s failed at '%c' (%d).", method, c, (int)c);
    }

    /**
     * @param type    type
     * @param handler method to run the loop against.
     * @throws Exception if it fails at any point.
     */
    public static int loopTest(CharType type, Consumer<Character> handler)
            throws Exception {
        int length = 0;

        // Assign the checker to the right Character.is* method.
        switch(type) {
            case Digit:
                checker = Character::isDigit;
                break;
            case Letter:
                checker = Character::isAlphabetic;
                break;
            case Other:
                checker = StringHandlerTest::isOther;
        }

        for(char i = 0; i < 256; i++) {
            boolean valid = checker.apply(i);

            try {
                handler.accept(i);
            } catch (IllegalArgumentException e) {
                if(valid) {
                    // Fail if i is valid and nothing should have been thrown.
                    fail(message(handler.toString(), i));
                } else {
                    // Continue if the throw was correct.
                    continue;
                }
            }

            // Fail if it should have thrown but didn't.
            if(!valid) {
                fail(message(handler.toString(), i));
            }

            length++;
        }

        // Success!
        return length;
    }
}
