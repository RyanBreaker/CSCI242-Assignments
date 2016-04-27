package csci242.assignments.facebooklite;

import java.util.Arrays;
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
public class CommandParser {
    private Scanner in = new Scanner(System.in);

    // TODO: refarctor this method, it's ugly af
    public Command getCommand() {
        Command command = null;

        while (true) {
            String[] line = in.nextLine().split("\\s+");
            char commandChar;
            CommandType type;

            if (line[0].length() != 1) {
                System.out.println("Error: First symbol in line not a command!");
                continue;
            }

            commandChar = line[0].toUpperCase().charAt(0);
            type = CommandType.getType(commandChar, line.length - 1);

            if (commandChar == 'X') {
                return new Command(CommandType.EXIT);
            }

            if (type == null) {
                System.out.println("Error: Inocrrect number of paramters for command!");
                continue;
            }

            if (line.length == 1)
                command = new Command(type);
            else
                command = new Command(type, Arrays.copyOfRange(line, 1, line.length));

            break;
        }

        return command;
    }
}
