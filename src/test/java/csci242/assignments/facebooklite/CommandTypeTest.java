package csci242.assignments.facebooklite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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

    @Before
    public void setUp() {

    }

    @Test
    public void testGetType() {
        CommandType valid = CommandType.NEW_PERSON;
        assertThat(valid, equalTo(CommandType.getType(valid.KEY, valid.PARAMS)));
    }

    @Test
    public void testGetType_invalid() {
        assertNull(CommandType.getType('Q', 3));
    }
}
