package csci242.assignments.facebooklite;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNull;
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
    public void testGetFirst() {
        String actual = testStrings[0];
        String result = testParams.getFirst();
        assertThat(actual, equalTo(result));
    }

    @Test
    public void testGetFirst_null() {
        testParams = new Params();
        assertNull(testParams.getFirst());
    }

    @Test
    public void testGetSecond() {
        String actual = testStrings[1];
        String result = testParams.getSecond();
        assertThat(actual, equalTo(result));
    }

    @Test
    public void testGetSecond_null() {
        testParams = new Params(testStrings[0]);
        assertNull(testParams.getSecond());
    }

    @Test
    public void testEquals() {
        Params otherParams = new Params(testStrings);
        assertThat(testParams, equalTo(otherParams));
    }

    @Test
    public void testEquals_empty() {
        Params otherParams = new Params();
        testParams = new Params();
        assertThat(testParams, equalTo(otherParams));
    }

    @Test
    public void testEquals_not() {
        Params otherParams = new Params(testStrings[0]);
        assertThat(testParams, not(equalTo(otherParams)));
    }
}
