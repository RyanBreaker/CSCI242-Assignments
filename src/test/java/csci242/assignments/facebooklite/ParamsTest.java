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
 * @edu.uwp.cs.242.assignment 5
 * @bugs None
 */
public class ParamsTest {
    private String[] testStrings = {"Foo", "Bar"};
    private Params testParams = new Params(testStrings);

    @Test
    public void testParamsCount() {
        int correctCount = testStrings.length;
        assertThat(testParams.count(), equalTo(correctCount));
    }

    @Test
    public void testGet() {
        int i = 0;
        String result = testParams.get(i);
        assertThat(result, equalTo(testStrings[i]));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_outOfBounds() {
        testParams.get(testStrings.length);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_negative() {
        testParams.get(-1);
    }
}
