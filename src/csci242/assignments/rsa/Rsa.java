package csci242.assignments.rsa;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Project Assignments.
 * Created on 2/3/16.
 * @author Ryan Breaker ryan@breaker.rocks
 */
public class Rsa {

    /**
     *
     * @param files FileInOut object to be used.
     * @param e First RSA key as an int.
     * @param n Second RSA key as an int.
     */
    public static void encrypt(FileInOut files, int e, int n) {

        // List of Strings, each representing 1 line of the input file.
        List<String> input = getFileContents(files.getInFile());
        // List of collections of Pairs, each collection representing 1 line of the input file.
        List<Pair[]> pairs = new ArrayList<Pair[]>();

        // Scanner for reading the input file.
        Scanner     fileIn  = files.getInFile();
        // PrintWriter for writing to the output file.
        PrintWriter fileOut = files.getOutFile();


        // For each line in input, create a new Pair array and add it to `pairs`.
        for(String line : input) {
            // Even-out the line's length if currently odd-lengthed by appending 'X'.
            if(line.length() % 2 != 0)
                line += 'X';

            // Size of the new Pair array; its even length / 2.
            int arrSize = line.length() / 2;
            // The new Pair array, later added to `pairs`.
            Pair[] newPairArray = new Pair[arrSize];

            // Divide the line into 2-character pieces and create Pairs out of each piece, adding it to the array.
            for(int i = 0, a = 0; i < line.length() && a < arrSize; i += 2, a++)
                newPairArray[a] = new Pair(line.charAt(i), line.charAt(i + 1));

            // Finally, add the array to its Collection.
            pairs.add(newPairArray);
        }


        // TODO: Combine this loop into the previous for efficiency.
        for(Pair[] pairArray : pairs) {
            for(Pair pair : pairArray) {
                int c = translateChar(pair.first) * 100 + translateChar(pair.second);
                fileOut.printf("%d\n", mod(e, n, c));
            }

            // Add '0' to indicate a new line in the original file.
            fileOut.println("0");
        }

        // Close the PrintWriter to flush (write) any new additions to the file.
        fileOut.close();
    }


    /**
     *
     * @param files FileInOut object to be used for I/O.
     * @param d First RSA key.
     * @param n Second RSA key.
     */
    public static void decrypt(FileInOut files, int d, int n) {
        List<String> input = getFileContents(files.getInFile());
        List<Pair[]> pairs = new ArrayList<Pair[]>();
    }


    /**
     * Translates the given char down such that the numeric value of ASCII 'A' is the origin at 0.
     * @param c Character to convert to int.
     * @return The associated int to c.
     */
    private static int translateChar(char c) {
        return c - 'A';
    }


    /**
     * Modulation algorithm for easier handling of massive, exponential numbers.
     * @param e First RSA key
     * @param n Second RSA key
     * @param p Pair's numeric ASCII sum (with 'A' subtracted)
     * @return Returns C = P^e % n
     */
    private static int mod(int e, int n, int p) {
        int result = 1;
        for(int j = 0; j < e; j++)
            result = (result * p) % n;

        return result;
    }


    /**
     * @param fileIn Scanner for reading input file.
     * @return Returns List of Strings, each element representing each line in the file.
     */
    private static List<String> getFileContents(Scanner fileIn) {
        List<String> fileContents = new ArrayList<String>();

        while(fileIn.hasNextLine())
            fileContents.add(fileIn.nextLine());

        return fileContents;
    }


    private Rsa() {
        throw new AssertionError("Class Rsa not meant to be instantiated!");
    }
}


class Pair {
    final char first, second;

    Pair(char first, char second) {
        this.first = first;
        this.second = second;
    }
}
