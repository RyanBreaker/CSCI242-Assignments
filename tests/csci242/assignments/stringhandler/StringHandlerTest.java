package csci242.assignments.stringhandler;

import java.util.function.Consumer;

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

    /**
     * @param start first char of range.
     * @param end   last char of range.
     * @param f     method to run the loop against.
     * @return number of characters looped.
     * @throws Exception if it fails at any point.
     */
    public static int loopTest(char start, char end, Consumer<Character> f)
            throws Exception {
        char i;

        for(i = start; i <= end; i++) {
            f.accept(i);
        }

        // Return length of range between start and end.
        return i - start;
    }
}
