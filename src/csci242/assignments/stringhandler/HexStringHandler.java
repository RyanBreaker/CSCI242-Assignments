package csci242.assignments.stringhandler;

/**
 * Implement a string parsing system, called StringHandler, in Java that uses
 * interfaces to specify common behavior and interface implementations to
 * specify specific behavior.
 * <p>
 * Handler for a hex string. Accepts characters 0-9, A-F, and a-f.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 3
 * @bugs None
 */
public class HexStringHandler implements StringHandler, Validator {

    static final int INVALID_NUMBER = -1;
    static final int NUMBER_SYSTEM = 16;
    static final int NUMBER_LETTER_MIN = 10;
    static final int NUMBER_LETTER_MAX = 16;
    static final String INVALIDHEX_ERROR = "Valid hex letter expected!";

    private boolean validHex = true;
    private int number = 0;


    protected boolean isValidHexLetter(char c) {
        return (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }


    @Override
    public void processDigit(char digit) {
        if (!Character.isDigit(digit)) {
            throw new IllegalArgumentException(PROCESSDIGIT_ERROR);
        }

        number += Character.getNumericValue(digit);
    }

    @Override
    public void processLetter(char letter) {
        if (!Character.isAlphabetic(letter)) {
            throw new IllegalArgumentException(PROCESSLETTER_ERROR);
        }

        // Don't bother trying if it's already invalid.
        if (validHex) {
            // Check if the given letter is invalid.
            if (!isValidHexLetter(letter)) {
                validHex = false;
                return;
            }

            number += letter - (Character.isUpperCase(letter) ? 'A' : 'a') + 10;
        }
    }

    @Override
    public void processOther(char other) {
        if (Character.isDigit(other) || isValidHexLetter(other)) {
            throw new IllegalArgumentException(PROCESSOTHER_ERROR);
        }

        validHex = false;
    }

    @Override
    public boolean isValid() {
        return validHex;
    }

    public int getNumber() {
        return (validHex ? number : INVALID_NUMBER);
    }
}
