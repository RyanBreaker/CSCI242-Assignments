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
        return friends.add(person) && person.friends.add(this);
    }

    boolean removeFriend(Person person) {
        return friends.remove(person) && person.friends.remove(this);
    }

    boolean isFriendsWith(Person person) {
        return friends.contains(person);
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Number of friends: %d",
                name, friends.size());
    }
}
