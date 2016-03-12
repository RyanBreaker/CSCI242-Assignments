package csci242.assignments.stringhandler;

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
public class HexStringHandler implements StringHandler, Validator {

    final int INVALID_NUMBER = -1;
    final int NUMBER_SYSTEM  = 16;
    final int NUMBER_LETTER_MIN = 10;
    final int NUMBER_LETTER_MAX = 16;


    private boolean validHex = false;
    private int number = 0;

    @Override
    public void reset() {
        validHex = false;
        number = 0;
    }

    @Override
    public void processDigit(char digit) {

    }

    @Override
    public void processLetter(char letter) {

    }

    @Override
    public void processOther(char other) {

    }

    @Override
    public boolean isValid() {
        return false;
    }

    public int getNumber() {
        return number;
    }
}
