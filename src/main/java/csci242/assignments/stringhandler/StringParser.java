package csci242.assignments.stringhandler;

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
public class StringParser {

    private StringHandler handler;


    public StringParser(StringHandler handler) {
        this.handler = handler;
    }


    public void parse(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                handler.processLetter(c);
            } else if (Character.isDigit(c)) {
                handler.processDigit(c);
            } else {
                handler.processOther(c);
            }
        }
    }


    public StringHandler getHandler() {
        return handler;
    }
}
