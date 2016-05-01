package csci242.assignments.facebooklite;

import java.util.Arrays;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command = (Command) o;
        return type == command.type && Arrays.equals(params, command.params);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(params);
        result = 31 * result + type.hashCode();
        return result;
    }
}
