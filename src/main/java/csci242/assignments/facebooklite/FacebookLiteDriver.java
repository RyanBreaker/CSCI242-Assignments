package csci242.assignments.facebooklite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
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
public class FacebookLiteDriver {

    private enum Mode {
        INTERACTIVE, FILE
    }

    private static Set<Person> people = new HashSet<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Mode mode = null;

        CommandParser parser = null;
        boolean done = false;

        println("<--| Facebook Lite |-->");
        while(mode == null) {
            println("(1) Interactive or (2) File?");
            String line = in.nextLine().trim();
            if (line.equals("1"))
                mode = Mode.INTERACTIVE;
            else if (line.equals("2"))
                mode = Mode.FILE;
        }

        if (mode == Mode.FILE) {
            while (parser == null) {
                try {
                    println("Enter filename");
                    parser = new CommandParser(new File(in.nextLine()));
                } catch (FileNotFoundException e) {
                    println("Invalid file");
                }
            }
        } else parser = new CommandParser();

        while(!done) {
            Command c = parser.getCommand();
            switch (c.type) {
                case EXIT:
                    done = true;
                    break;
                case NEW_PERSON:
                    people.add(new Person(c.params.getFirst()));
                    break;
                case ADD_FRIEND:
                    addFriend(c.params.getFirst(), c.params.getSecond());
                    break;
                case UNFRIEND:
                    unfriend(c.params.getFirst(), c.params.getSecond());
                    break;

            }
        }
    }

    private static class Pair {
        final Person p1;
        final Person p2;

        Pair(Person p1, Person p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        static Pair getPeople(String name1, String name2) {
            Person p1 = null, p2 = null;
            for (Person p : people) {
                if (p.name.equals(name1))
                    p1 = p;
                else if (p.name.equals(name2))
                    p2 = p;
                if (p1 != null && p2 != null)
                    break;
            }

            if (p1 == null || p2 == null)
                return null;
            return new Pair(p1, p2);
        }
    }

    private static boolean addFriend(String name1, String name2) {
        Pair pair = Pair.getPeople(name1, name2);
        return pair != null && pair.p1.addFriend(pair.p2);
    }

    private static boolean unfriend(String name1, String name2) {
        Pair pair = Pair.getPeople(name1, name2);
        return pair != null && pair.p1.removeFriend(pair.p2);
    }

    private static void println(String line) {
        System.out.println(line);
    }
}
