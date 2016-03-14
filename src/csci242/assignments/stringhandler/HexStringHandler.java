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
public class HexStringHandler implements StringHandler, Validator {

    final int INVALID_NUMBER = -1;
    final int NUMBER_SYSTEM  = 16;
    final int NUMBER_LETTER_MIN = 10;
    final int NUMBER_LETTER_MAX = 16;


    private boolean validHex = true;
    private int number = 0;


    protected boolean isValidHexLetter(char c) {
        return (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }


    @Override
    public void processDigit(char digit) {
        if(!Character.isDigit(digit)) {
            throw new IllegalArgumentException(PROCESSDIGIT_ERROR);
        }

        number += Character.getNumericValue(digit);
    }

    @Override
    public void processLetter(char letter) {
        if(!isValidHexLetter(letter)) {
            throw new IllegalArgumentException(PROCESSLETTER_ERROR);
        }

        number += letter - (Character.isUpperCase(letter) ? 'A' : 'a') + 1;
    }

    @Override
    public void processOther(char other) {
        if(Character.isDigit(other) || isValidHexLetter(other)) {
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
