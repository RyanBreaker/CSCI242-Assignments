package csci242.assignments.facebooklite;

import java.util.HashSet;
import java.util.Set;

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
class Person {
    final String name;
    private Set<Person> friends = new HashSet<>();

    Person(String name) {
        this.name = name;
    }


    boolean addFriend(Person person) {
        //noinspection SimplifiableIfStatement
        if (person == this || person.friends.contains(this))
            return false;
        return friends.add(person);
    }

    boolean removeFriend(Person person) {
        return friends.remove(person) || person.friends.remove(this);
    }

    boolean isFriendsWith(Person person) {
        return friends.contains(person) || person.friends.contains(this);
    }
}
