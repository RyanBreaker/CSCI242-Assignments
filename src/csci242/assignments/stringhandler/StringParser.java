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
 * @edu.uwp.cs.242.assignment 3
 * @bugs None
 */
public class StringParser {

    private StringHandler handler;


    public StringParser(StringHandler handler) {
        this.handler = handler;
    }


    public void parse(String s) {
        for(char c : s.toCharArray()) {
            if(Character.isAlphabetic(c)) {
                handler.processLetter(c);
            } else if(Character.isDigit(c)) {
                handler.processDigit(c);
            } else {
                handler.processOther(c);
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;
        StringParser parser = new StringParser(new HexStringHandler());

        // HexStringHandler
        System.out.print("Enter a hexadecimal number >  ");
        line = input.nextLine();

        parser.parse(line);

        {
            HexStringHandler handler = (HexStringHandler)parser.handler;

            if(handler.isValid()) {
                System.out.println(line + " = " + handler.getNumber());
            } else {
                System.out.println(line + " is not a valid hex number.");
            }
        }

        System.out.println();

        // PasswordSecurityHandler
        parser = new StringParser(new PasswordSecurityHandler());

        System.out.println("A strong password has at least eight characters " +
                "and contains at least one digit and one special characters.");
        System.out.print("Enter a password > ");
        line = input.nextLine();

        parser.parse(line);

        {
            PasswordSecurityHandler handler = (PasswordSecurityHandler)parser.handler;
            System.out.println(line + "'s security is: " + handler.securityLevel());
        }
    }
}
