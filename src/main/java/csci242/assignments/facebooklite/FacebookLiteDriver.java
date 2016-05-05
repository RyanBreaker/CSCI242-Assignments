package csci242.assignments.facebooklite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Optional;
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
        boolean running = true;

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
                    println("Enter filename:");
                    parser = new CommandParser(new File(in.nextLine()));
                } catch (FileNotFoundException e) {
                    println("Invalid file");
                }
            }
        } else parser = new CommandParser();

        // Run loop
        while(running) running = run(parser);
    }

    /**
     * The primary method for running functionality of the program.
     * @param parser The CommandParser to use.
     * @return {@code false} if an EXIT command was given, {@code true} otherwise.
     */
    private static boolean run(CommandParser parser) {
        Command c = parser.getCommand();
        String name1 = c.params.getFirst();
        String name2 = c.params.getSecond();
        Optional<String> error = Optional.empty();

        // Print information
        print("'Command: " + c.type.KEY);
        if (name1 != null && name2 == null)
            print(" " + name1);
        else if (name2 != null) {
            if (name2.equals(name1))
                error = Optional.of("Error: Given names are the same!");
            print(" " + name1 + " " + name2);
        }
        print("' > ");

        if (error.isPresent()) {
            println(error.get());
            return true;
        }

        if (c.type == CommandType.EXIT) {
            println("Leaving FacebookLite.");
            return false;
        }

        switch (c.type) {
            case NEW_PERSON:
                if (people.add(new Person(c.params.getFirst())))
                    println("Added " + name1 + ".");
                else
                    println("Error: " + name1 + " already exists!");
                break;

            case ADD_FRIEND:
                if (addFriend(c.params.getFirst(), c.params.getSecond()))
                    println(and(name1, name2) + "are now friends.");
                else
                    println("Error: " + and(name1, name2) +
                            "are already friends!");
                break;

            case UNFRIEND:
                if (unFriend(c.params.getFirst(), c.params.getSecond()))
                    println(and(name1, name2) + "are no longer friends.");
                else
                    println("Error: " + and(name1, name2) +
                            "weren't already friends!");
                break;

            case LIST_FRIENDS:
                Person p = getPerson(name1);
                if (p == null) {
                    println("Error: " + name1 + " does not exist!");
                    break;
                }
                //noinspection ConstantConditions
                char end = name1.charAt(name1.length()-1);
                if (end == 's' || end == 'S')
                    print(name1 + "' friends: ");
                else
                    print(name1 + "'s friends: ");
                println(list(p));
                break;

            case QUERY_FRIENDS:
                Optional<Person> op1 = Optional.ofNullable(getPerson(name1));
                Optional<Person> op2 = Optional.ofNullable(getPerson(name2));

                if (op1.isPresent() && op2.isPresent()) {
                    boolean areFriends = op1.get().isFriendsWith(op2.get());
                    println(and(name1, name2) + "are" +
                            (areFriends ? " " : " NOT ") + "friends.");
                } else {
                    if (!op1.isPresent())
                        println("Error: " + name1 + " does not exist!");
                    if (!op2.isPresent())
                        println("Error: " + name2 + " does not exist!");
                }
        }
        return true;
    }

    private static String and(String name1, String name2) {
        return name1 + " and " + name2 + " ";
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

    private static Person getPerson(String name) {
        for (Person p : people) {
            if (p.name.equals(name))
                return p;
        }
        return null;
    }

    private static boolean addFriend(String name1, String name2) {
        Pair pair = Pair.getPeople(name1, name2);
        return pair != null && pair.p1.addFriend(pair.p2);
    }

    private static boolean unFriend(String name1, String name2) {
        Pair pair = Pair.getPeople(name1, name2);
        return pair != null && pair.p1.removeFriend(pair.p2);
    }

    private static String list(Person person) {
        String friends = "[ ";
        for (Person p : person.getFriends())
            friends += p.name + " ";
        friends += "]";

        return friends;
    }

    private static void println(String line) {
        System.out.println(line);
    }
    private static void print(String line) {
        System.out.print(line);
    }
}
