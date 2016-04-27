package csci242.assignments.facebooklite;

/**
 * Short description.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 5
 * @bugs None
 */
enum CommandType {
    NEW_PERSON('P', 1),
    ADD_FRIEND('F', 2),
    UNFRIEND('U', 2),
    LIST_FRIENDS('L', 1),
    QUERY_FRIENDS('Q', 2),
    EXIT('X', 0);

    final char KEY;
    final int PARAMS;
    CommandType(char key, int params) {
        this.KEY = key;
        this.PARAMS = params;
    }

    static CommandType getType(char key, int params) {
        for (CommandType c : values()) {
            if (c.KEY == key && c.PARAMS == params) {
                return c;
            }
        }
        return null;
    }
}
