package csci242.assignments.stringhandler;

import com.sun.jdi.CharType;

import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.junit.Assert.fail;

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
public class StringHandlerTestHelper {

    private StringHandlerTestHelper() {
    }

    private static boolean isOther(char c) {
        return !(Character.isDigit(c) || Character.isAlphabetic(c));
    }

    private static void failWithMessage(CharType charType, char c) {
        fail(String.format("Type %s failed at '%c' (%d).", charType, c, (int) c));
    }

    protected static void loopTest(Predicate<Character> validator,
                                   Consumer<Character> processor, String processorName)
            throws Exception {

        for (char i = 0; i < 256; i++) {
            boolean valid = validator.test(i);

            try {
                processor.accept(i);
            } catch (IllegalArgumentException e) {
                if (valid) {
                    // Exception incorrectly thrown.
                    fail(processorName + " threw incorrectly at: " + i);
                }
                // Exception correctly thrown, continue.
                continue;
            }

            if (!valid) {
                // Exception should have been thrown.
                fail(processorName + " should have thrown at: " + i);
            }
        }
    }
}
