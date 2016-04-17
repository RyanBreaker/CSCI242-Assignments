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
 * @edu.uwp.cs.242.assignment 2
 * @bugs None
 */
public class IntlTelephoneStringHandler implements StringHandler, Validator {

    private boolean hasPlus = false;
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

        badChar = true;
    }

    @Override
    public void processOther(char other) {
        if (Character.isDigit(other) || Character.isAlphabetic(other)) {
            throw new IllegalArgumentException(PROCESSOTHER_ERROR);
        }

        if (length == 0 && other == '+') {
            hasPlus = true;
            length++;
        } else {
            badChar = true;
        }
    }

    @Override
    public boolean isValid() {
        return (length >= 12 && length <= 15) && hasPlus && !badChar;
    }
}
