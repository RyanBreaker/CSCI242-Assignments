package csci242.assignments.facebooklite;

import java.io.File;
import java.io.FileNotFoundException;
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
class CommandParser {
    private Scanner in;

    CommandParser() {
        in = new Scanner(System.in);
    }

    CommandParser(File file) throws FileNotFoundException {
        in = new Scanner(file);
    }

    Command getCommand() {
        Command command = null;

        while (command == null) {
            CommandType type;
            char commandChar;
            String[] line = in.nextLine().split("\\s+");


            if (line[0].length() != 1) {
                System.out.printf("Error: '%s' is not a valid command\n", line[0]);
                continue;
            }

            commandChar = line[0].toUpperCase().charAt(0);
            if (commandChar == 'X') return new Command(CommandType.EXIT);

            type = CommandType.getType(commandChar, line.length - 2);
            if (type == CommandType.INVALID) {
                System.out.printf("Error: Bad format for command '%c'\n", commandChar);
                continue;
            }

            if (line.length == 1)
                command = new Command(type);
            else
                command = new Command(type, Arrays.copyOfRange(line, 1, line.length));
        }

        return command;
    }
}
