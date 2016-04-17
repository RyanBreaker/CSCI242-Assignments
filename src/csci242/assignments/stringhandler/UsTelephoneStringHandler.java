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
public class UsTelephoneStringHandler implements StringHandler, Validator {

    private boolean badChar = false;
    private int length = 0;


    @Override
    public void processDigit(char digit) {
        if (!Character.isDigit(digit)) {
            throw new IllegalArgumentException(PROCESSDIGIT_ERROR);
        }

        length++;
    }

    @Override
    public void processLetter(char letter) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException(PROCESSLETTER_ERROR);
        }

        length++;
        badChar = true;
    }

    @Override
    public void processOther(char other) {
        if (Character.isAlphabetic(other) || Character.isDigit(other)) {
            throw new IllegalArgumentException(PROCESSOTHER_ERROR);
        }

        length++;
        badChar = true;
    }

    @Override
    public boolean isValid() {
        // True if no bad character found and length is 10.
        return !badChar && length == 10;
    }
}
