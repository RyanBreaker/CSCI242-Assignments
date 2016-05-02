package csci242.assignments.facebooklite;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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
public class CommandTypeTest {
    @Test
    public void testGetType() {
        CommandType valid = CommandType.NEW_PERSON;
        assertThat(valid, equalTo(CommandType.getType(valid.KEY, valid.PARAMS)));
    }

    @Test
    public void testGetType_invalid() {
        assertThat(CommandType.getType('Q', 3), equalTo(CommandType.INVALID));
    }
}
