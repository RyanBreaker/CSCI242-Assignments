package csci242.assignments.madlib;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

/**
 * Short description.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 4
 * @bugs None
 */
public class ArrayBagTest {

    ArrayBag<Integer> testBag;


    @Before
    public void setUp() {
        testBag = new ArrayBag<>();
    }


    @Test
    public void testAddElements() {
        int max = 100;

        for(int i = 0; i < max; i++) {
            testBag.add(i);
        }

        assertThat(max, equalTo(testBag.getSize()));
        assertThat(max, equalTo(testBag.getCapacity()));
    }

    @Test
    public void testTrimToSize() {
        int max = 15;

        for (int i = 0; i < max; i++) {
            testBag.add(i);
        }

        assertThat(testBag.getSize(), equalTo(max));
        assertThat(testBag.getCapacity(), equalTo(20));
        testBag.trimToSize();
        assertThat(testBag.getCapacity(), equalTo(max));
    }

    @Test
    public void testCombine() {
        ArrayBag<Integer> otherBag = new ArrayBag<>();
        ArrayBag<Integer> combined;
        int interval = 25;

        for(int i = 0; i < interval; i++)
            testBag.add(i);
        for(int i = interval; i < interval*2; i++)
            otherBag.add(i);

        combined = ArrayBag.combine(testBag, otherBag);

        assertThat(combined.getSize(), equalTo(interval*2));
        assertTrue(combined.grab() >= 0 && combined.grab() < interval*2);
    }
}
