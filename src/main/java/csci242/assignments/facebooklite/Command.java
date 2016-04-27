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
class Command {
    final String[] params;
    final CommandType type;

    Command(CommandType type, String... params) {
        this.type = type;
        this.params = params;
    }
}
