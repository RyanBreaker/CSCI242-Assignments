package csci242.assignments.madlib;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 4
 * @bugs None
 */
public class ArrayBagTest {

    private ArrayBag<Integer> testBag;

    @Before
    public void setUp() {
        testBag = new ArrayBag<>();
    }


    //region Constructors
    @Test
    public void testConstructorWithInitialCapactiy() {
        testBag = new ArrayBag<>(5);
        assertThat(testBag.getCapacity(), equalTo(5));
    }

    @Test
    public void testConstructorWith0InitialCapacity() {
        testBag = new ArrayBag<>(0);
        assertThat(testBag.getCapacity(),
                equalTo(ArrayBag.DEFAULT_INITIAL_CAPACITY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeInitialCapactiy() {
        testBag = new ArrayBag<>(-5);
    }
    //endregion

    //region Add
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
    public void testAddMany() {
        testBag.addMany(1, 2, 3, 4, 5);
        assertThat(testBag.getSize(), equalTo(5));
    }
    //endregion

    //region Remove
    @Test
    public void testRemove() {
        for (int i = 0; i < 10; i++) {
            testBag.add(i);
        }
        assertTrue(testBag.remove(5));
        assertFalse(testBag.remove(5));
    }

    @Test
    public void testRemoveWhileEmpty() {
        assertTrue(testBag.isEmpty());
        assertFalse(testBag.remove(0));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        testBag.remove(null);
    }
    //endregion

    //region Intance Methods
    @Test
    public void testGrab() {
        testBag.add(5);
        assertThat(testBag.grab(), equalTo(5));
        assertThat(testBag.getSize(), equalTo(0));
    }

    @Test(expected = IllegalStateException.class)
    public void testGrabWhenEmpty() {
        testBag.grab();
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
    public void testEnsureCapacityLarge() {
        testBag.ensureCapacity(50);
        assertThat(testBag.getCapacity(), equalTo(50));
    }

    @Test
    public void testEnsureCapacityUnderLength() {
        int capacity = testBag.getCapacity();
        testBag.ensureCapacity(capacity / 2);
        assertThat(testBag.getCapacity(), equalTo(capacity));
    }

    @Test
    public void testEnsureCapacityUseDefault() {
        int oldCapacity = testBag.getCapacity();
        int testCapacity = (int) (oldCapacity * 1.5);
        int expectedCapacity = oldCapacity + ArrayBag.MIN_EXPAND;

        testBag.ensureCapacity(testCapacity);
        assertThat(testBag.getCapacity(), equalTo(expectedCapacity));
    }

    @Test
    public void testCountOccurances() {
        int smaller = 5;
        int larger = 10;

        for (int i = 0; i < smaller; i++) {
            testBag.add(smaller);
        }
        for (int i = 0; i < larger; i++) {
            testBag.add(larger);
        }

        assertThat(testBag.countOccurances(smaller), equalTo(smaller));
        assertThat(testBag.countOccurances(larger), equalTo(larger));
    }

    @Test(expected = NullPointerException.class)
    public void testCountOccurancesWithNull() {
        testBag.countOccurances(null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testClone() throws CloneNotSupportedException {
        for (int i = 0; i < 10; i++) {
            testBag.add(i);
        }

        ArrayBag<Integer> clone;
        clone = (ArrayBag<Integer>) testBag.clone();


        assertThat(testBag.getSize(), equalTo(clone.getSize()));
        assertThat(testBag.getCapacity(), equalTo(clone.getCapacity()));

        assertThat(clone.countOccurances(testBag.grab()), equalTo(1));
    }
    //endregion

    //region Static Methods
    @Test
    public void testCombine() {
        ArrayBag<Integer> otherBag = new ArrayBag<>();
        ArrayBag<Integer> combined;
        int interval = 25;

        for (int i = 0; i < interval; i++)
            testBag.add(i);
        for (int i = interval; i < interval * 2; i++)
            otherBag.add(i);

        combined = ArrayBag.combine(testBag, otherBag);

        assertThat(combined.getSize(), equalTo(interval * 2));
        assertTrue(combined.grab() >= 0 && combined.grab() < interval * 2);
    }
    //endregion
}
