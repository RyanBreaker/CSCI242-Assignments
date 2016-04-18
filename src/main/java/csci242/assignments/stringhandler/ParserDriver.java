package csci242.assignments.stringhandler;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Implement a string parsing system, called StringHandler, in Java that uses
 * interfaces to specify common behavior and interface implementations to
 * specify specific behavior.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 3
 * @bugs None
 */
public class ParserDriver {

    public static void main(String[] args) {
        PrintStream out = System.out;
        Scanner in = new Scanner(System.in);
        String line;
        StringParser parser;


        // HexStringHandler
        parser = new StringParser(new HexStringHandler());
        out.print("Enter a hexadecimal number: ");
        line = in.nextLine();
        parser.parse(line);

        {
            HexStringHandler handler = (HexStringHandler) parser.getHandler();

            if (handler.isValid()) {
                out.println(line + " = " + handler.getNumber());
            } else {
                out.println(line + " is not a valid hexadecimal number.");
            }
        }

        out.println();


        // PasswordSecurityHandler
        parser = new StringParser(new PasswordSecurityHandler());
        out.println("A secure password consists of at least 8 " +
                "characters, 3 of are numbers and 3 of which are special.");
        out.print("Enter a password: ");
        line = in.nextLine();
        parser.parse(line);

        {
            PasswordSecurityHandler handler =
                    (PasswordSecurityHandler) parser.getHandler();

            out.println(line + " is a " + handler.securityLevel() +
                    " password.");
        }

        out.println();


        // UsTelephoneStringHandler
        parser = new StringParser(new UsTelephoneStringHandler());
        out.println("");
        out.print("Enter a US phone number: ");
        line = in.nextLine();
        parser.parse(line);

        {
            UsTelephoneStringHandler handler =
                    (UsTelephoneStringHandler) parser.getHandler();

            if (handler.isValid()) {
                out.println(line + " is a valid US phone number.");
            } else {
                out.println(line + " is not a valid US phone number.");
            }
        }

        out.println();


        // IntlTelephoneStringHandler
        parser = new StringParser(new IntlTelephoneStringHandler());
        out.println("");
        out.print("Enter an international phone number: ");
        line = in.nextLine();
        parser.parse(line);

        {
            IntlTelephoneStringHandler handler =
                    (IntlTelephoneStringHandler) parser.getHandler();

            if (handler.isValid()) {
                out.println(line +
                        " is a valid international phone number.");
            } else {
                out.println(line +
                        " is not a valid international phone number.");
            }
        }

        out.println("Done!");
    }
}
