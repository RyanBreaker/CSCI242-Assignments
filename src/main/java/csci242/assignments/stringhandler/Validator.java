package csci242.assignments.stringhandler;

/**
 * Implement a string parsing system, called StringHandler, in Java that uses
 * interfaces to specify common behavior and interface implementations to
 * specify specific behavior.
 * <p>
 * Validator
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 3
 * @bugs None
 */
public interface Validator {

    /**
     * @return whether the string is valid or not.
     */
    boolean isValid();
}
