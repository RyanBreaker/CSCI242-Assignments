package csci242.assignments.stringhandler;

import java.util.Scanner;

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
public class ParserDriver {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        StringParser parser;


        parser = new StringParser(new HexStringHandler());
        System.out.print("Enter a hexadecimal number: ");
        line = in.nextLine();
        parser.parse(line);

        {
            HexStringHandler handler = (HexStringHandler)parser.getHandler();

            if(handler.isValid()) {
                System.out.println(line + " = " + handler.getNumber());
            } else {
                System.out.println(line + " is not a valid hexadecimal number.");
            }
        }

        
    }
}
