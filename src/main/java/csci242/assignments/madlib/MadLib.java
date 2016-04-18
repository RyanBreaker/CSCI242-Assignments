package csci242.assignments.madlib;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static csci242.assignments.madlib.MadLib.ItemType.*;

/**
 * Play the MadLib word game.
 *
 * <p>Mad Libs is a phrasal template word game where one player prompts others
 * for a list of words to substitute for blanks in a story, before reading the –
 * often comical or nonsensical – story aloud. The game is frequently played as
 * a party game or as a pastime.
 *
 * <p>Mad Libs are copyrighted by Price Stern Sloan a division of
 * Penguin Putnam Books for Young Readers, New York.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 4
 * @bugs None
 */
public class MadLib {

    // Using final to prevent accidental reassignment
    private final static Scanner in = new Scanner(System.in);
    private final static Map<ItemType, ArrayBag<String>> words = new HashMap<>();

    enum ItemType {
        adjectives, nouns, game, number, plant, place,
        bodyPart {
            @Override
            public String toString() {
                return "body part";
            }
        },
        verbs {
            @Override
            public String toString() {
                return "verbs ending in 'ing'";
            }
        },
        pluralNouns {
            @Override
            public String toString() {
                return "plural nouns";
            }
        }

    }

    /**
     * Main MadLib driver.
     *
     * <p>Main performs a number of functions:
     * <ol>
     *     <li>Declares and instantiates the word ArrayBags.</li>
     *     <li>Prints a header so the user knows what game is being played.</li>
     *     <li>Prompts the user for space separated lists of words that are
     *     placed in the corresponding ArrayBag.</li>
     *     <li>Prints the title of the MadLib.</li>
     *     <li>Prints the story of the Mad Lib using the ArrayBags to choose
     *     the missing words.</li>
     * </ol>
     *
     * @param args commandline arguments.
     */
    public static void main(String[] args) {
        intro();

        putWords(3, adjectives);
        putWords(3, nouns);
        putWords(4, pluralNouns);
        putWords(1, game);
        putWords(4, verbs);
        putWords(1, plant);
        putWords(1, bodyPart);
        putWords(1, place);
        putWords(1, number);

        copyright();

        println(makeMadLib());
    }

    private static void intro() {
        println("--------");
        println("MAD LIBS");
        println("--------");
        println();
    }
    private static void copyright() {
        println("From VACATION FUN MAD LIBS® • Copyright © 1988 by Price Stern Sloan,\n" +
                "a division of Penguin Putnam Books for Young Readers, New York.");
        println();
    }

    /**
     * Shorthand method for words.put(ItemType, getItems(n, ItemType))
     */
    private static void putWords(int number, ItemType type) {
        words.put(type, getItems(number, type));
        println();
    }

    private static ArrayBag<String> getItems(int number, ItemType item) {
        String[] line;
        ArrayBag<String> items = new ArrayBag<>();

        println(String.format("Please type in %d %s, separated by spaces.", number, item));
        println("Press the <return> key after the final entry.");

        line = in.nextLine().toUpperCase().split("\\s+");
        items.addMany(line);

        return items;
    }

    private static String makeMadLib() {
        final int section = 80;
        final String header = "-- VACATION --\n\n";

        StringBuilder madLibPretty;
        String madLib =
                "A vacation is when you take a trip to some %ADJ% place with "  +
                "your %ADJ% family. Usually you go to some place that is near " +
                "a/an %NOUN% or up on a/an %NOUN%. A good vacation place is " +
                "one where you can ride %PNOUN% or play %GAME% or go hunting for %PNOUN%. " +
                "I like to spend my time %VERB% or %VERB%. When parents go on a " +
                "vacation, they spend their time eating three %PNOUN% a day, and " +
                "fathers play golf, and mothers sit around %VERB%. Last summer, my " +
                "little brother fell in a/an %NOUN% and got poison %PLANT% all over his " +
                "%BPART%. My family is going to go to (the) %PLACE%, and I will " +
                "practice %VERB%. Parents need vacations more than kids because parents " +
                "are always very %ADJ% and because they have to work %NUMBER% hours " +
                "every day all year making enough %PNOUN% to pay for the vacation.";

        madLib = plugWords(adjectives,  "ADJ",    madLib);
        madLib = plugWords(nouns,       "NOUN",   madLib);
        madLib = plugWords(pluralNouns, "PNOUN",  madLib);
        madLib = plugWords(game,        "GAME",   madLib);
        madLib = plugWords(verbs,       "VERB",   madLib);
        madLib = plugWords(plant,       "PLANT",  madLib);
        madLib = plugWords(bodyPart,    "BPART",  madLib);
        madLib = plugWords(place,       "PLACE",  madLib);
        madLib = plugWords(number,      "NUMBER", madLib);

        // Make it pretty by replacing a space with a '\n' every ~80 characters
        madLibPretty = new StringBuilder(madLib);
        for (int i = 0; i < madLibPretty.length()-section; i += section) {
            String sub = madLibPretty.substring(i, i + section);

            madLibPretty.setCharAt(sub.lastIndexOf(' ') + i, '\n');
        }

        return header + madLibPretty.toString();
    }

    private static String plugWords(ItemType type, String shorthand, String template) {
        ArrayBag<String> currentWords = words.get(type);

        // "WORD" to "%WORD%"
        shorthand = String.format("%%%s%%", shorthand);
        while (template.contains(shorthand)) {
            template = template.replaceFirst(shorthand, currentWords.grab());
        }
        return template;
    }

    private static void println() {
        System.out.println();
    }
    private static void println(String line) {
        System.out.println(line);
    }
}
