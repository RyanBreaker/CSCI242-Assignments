package csci242.assignments.facebooklite;

import java.util.Scanner;

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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Mode whatDo = null;

        println("<--| Facebook Lite |-->");
        while(whatDo == null) {
            println("(1) Interactive or (2) File?");
            String line = in.nextLine().trim();
            if (line.equals("1"))
                whatDo = Mode.INTERACTIVE;
            else if (line.equals("2"))
                whatDo = Mode.FILE;
        }

        println("done");
    }

    private static void println(String line) {
        System.out.println(line);
    }
}
