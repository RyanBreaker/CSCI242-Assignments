package csci242.assignments.stringhandler;

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
public interface StringHandler {

    String PROCESSDIGIT_ERROR = "Digit expected but not given!";
    String PROCESSLETTER_ERROR = "Letter expected but not given!";
    String PROCESSOTHER_ERROR = "Other expected but not given!";

    /**
     * Makes sure the given char is a digit (0-9).
     *
     * @param digit The char to verify.
     */
    void processDigit(char digit);

    /**
     * Makes sure the given char is a letter.
     *
     * @param letter The char to verify.
     */
    void processLetter(char letter);

    /**
     * Makes sure the given char is neither a digit nor a letter.
     *
     * @param other The char to verify.
     */
    void processOther(char other);
}
