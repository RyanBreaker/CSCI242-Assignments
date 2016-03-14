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
 * @edu.uwp.cs.242.assignment 3
 * @bugs None
 */
public class StringHandlerTest {

    protected enum CharType {
        Digit, Letter, Other
    }

    private static Function<Character, Boolean> checker;


    private StringHandlerTest() {}

    private static boolean isOther(char c) {
        return !(Character.isDigit(c) || Character.isAlphabetic(c));
    }

    private static void failWithMessage(CharType charType, char c) {
        fail(String.format("Type %s failed at '%c' (%d).", charType, c, (int)c));
    }

    /**
     * @param charType    type
     * @param handler method to run the loop against.
     * @throws Exception if it fails at any point.
     */
    public static int loopTest(CharType charType, Consumer<Character> handler)
            throws Exception {
        int length = 0;

        // Assign the checker to the right Character.is* method.
        switch(charType) {
            case Digit:
                checker = Character::isDigit;
                break;
            case Letter:
                checker = Character::isAlphabetic;
                break;
            case Other:
                checker = StringHandlerTest::isOther;
        }

        // Test time!
        for(char i = 0; i < 256; i++) {
            // Is the current character valid for what we're checking?
            boolean valid = checker.apply(i);

            try {
                handler.accept(i);
            } catch (IllegalArgumentException e) {
                if(valid) {
                    // Fail if i is valid and nothing should have been thrown.
                    failWithMessage(charType, i);
                } else {
                    // Continue if the throw was correct.
                    continue;
                }
            }

            // Fail if it should have thrown but didn't.
            if(!valid) {
                failWithMessage(charType, i);
            }

            length++;
        }

        // Success!
        return length;
    }
}
