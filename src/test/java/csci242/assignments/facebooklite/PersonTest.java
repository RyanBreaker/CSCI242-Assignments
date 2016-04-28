package csci242.assignments.facebooklite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class PersonTest {
    private Person testPerson;
    private Person otherPerson;

    /**
     * Convenience method for adding otherPerson to testPerson.
     */
    private void addOtherPersonToTestPerson() {
        assertTrue(testPerson.addFriend(otherPerson));
    }

    @Before
    public void setUp() {
        testPerson = new Person("Bob");
        otherPerson = new Person("Jane");
    }


    //region addFriend
    @Test
    public void testAddFriend() {
        boolean result = testPerson.addFriend(otherPerson);
        assertTrue(result);
    }

    @Test
    public void testAddFriend_alreadyFriends() {
        addOtherPersonToTestPerson();

        boolean otherPersonAdd = otherPerson.addFriend(testPerson);
        boolean testPersonAdd = testPerson.addFriend(otherPerson);

        assertFalse(otherPersonAdd);
        assertFalse(testPersonAdd);
    }

    @Test
    public void testAddFriend_self() {
        boolean testPersonAddToSelf = testPerson.addFriend(testPerson);
        assertFalse(testPersonAddToSelf);
    }
    //endregion

    //region isFriendsWith
    @Test
    public void testIsFriendsWith() {
        addOtherPersonToTestPerson();

        boolean testPersonIsFriends = testPerson.isFriendsWith(otherPerson);
        boolean otherPersonIsFriends = otherPerson.isFriendsWith(testPerson);

        assertTrue(testPersonIsFriends);
        assertTrue(otherPersonIsFriends);
    }

    @Test
    public void testIsFriendsWith_not() {
        boolean testPersonIsFriends = testPerson.isFriendsWith(otherPerson);
        boolean otherPersonIsFriends = otherPerson.isFriendsWith(testPerson);

        assertFalse(testPersonIsFriends);
        assertFalse(otherPersonIsFriends);
    }

    @Test
    public void testIsFriendsWith_self() {
        boolean testPersonIsFriendsWithSelf = testPerson.isFriendsWith(testPerson);
        assertFalse(testPersonIsFriendsWithSelf);
    }
    //endregion

    //region removeFriend
    @Test
    public void testRemoveFriend() {
        addOtherPersonToTestPerson();

        boolean removedFromTest = testPerson.removeFriend(otherPerson);
        boolean removedFromOther = otherPerson.removeFriend(testPerson);

        assertTrue(removedFromTest);
        assertFalse(removedFromOther);
    }

    @Test
    public void testRemoveFriend_fromOther() {
        addOtherPersonToTestPerson();

        boolean removedFromOther = otherPerson.removeFriend(testPerson);
        boolean removedFromTest = testPerson.removeFriend(otherPerson);

        assertTrue(removedFromOther);
        assertFalse(removedFromTest);;
    }
    //endregion
}
