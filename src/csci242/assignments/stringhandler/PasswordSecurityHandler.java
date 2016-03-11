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
public class PasswordSecurityHandler implements StringHandler {

    final String SECURITY_LEVEL_WEAK   = "weak";
    final String SECURITY_LEVEL_MEDIUM = "medium";
    final String SECURITY_LEVEL_STRONG = "string";



    /**
     * Number of characters in the password.
     */
    private int length = 0;
    /**
     * Flag to indicate whether a digit was found.
     */
    private boolean digit = false;
    /**
     * Flag to indicate whether a non-alphanumeric character was found.
     */
    private boolean otherCharacter = false;


    public String securityLevel() {
        return "";
    }

    //region StringHandler methods
    /**
     * @param digit The char to verify.
     */
    @Override
    public void processDigit(char digit) {
        if (digit < '0' || digit > '9') {
            throw new IllegalArgumentException(PROCESSDIGIT_ERROR);
        }

        this.digit = true;
        length++;
    }

    /**
     * @param letter The char to verify.
     */
    @Override
    public void processLetter(char letter) {
        if (letter < 'A' || letter > 'z') {
            throw new IllegalArgumentException(PROCESSLETTER_ERROR);
        }

        length++;
    }

    /**
     * @param other The char to verify.
     */
    @Override
    public void processOther(char other) {
        if ((other >= '0' && other <= '9') || (other >= 'A' && other <'z')) {
            throw new IllegalArgumentException(PROCESSOTHER_ERROR);
        }

        otherCharacter = true;
        length++;
    }
    //endregion

    /**
     * @return The password's length.
     */
    //region Getters
    protected int getLength() {
        return length;
    }

    /**
     * @return Whether or not the password has a digit.
     */
    protected boolean getDigit() {
        return digit;
    }

    /**
     * @return Whether or not the password has a non-alphanumeric character.
     */
    protected boolean getOtherCharacter() {
        return otherCharacter;
    }

    //endregion
}
