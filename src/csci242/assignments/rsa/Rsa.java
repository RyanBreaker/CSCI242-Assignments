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

        // PrintWriter for writing to the output file.
        PrintWriter fileOut = files.getOutFile();


        // For each line in input, grab each pair, encrypt its ASCII sum, and write it to the output.
        for(String line : input) {
            // Even-out the line's length if currently odd-lengthed by appending 'X'.
            if(line.length() % 2 != 0)
                line += "X";

            // Divide the line into 2-character pieces then translate, encrypt, and write them.
            for(int i = 0, c; i < line.length(); i += 2) {
                c = translateChar(line.charAt(i)) * 100 + translateChar(line.charAt(i+1));
                fileOut.println(mod(e, n, c));
            }

            // '0' to represent new line in original file.
            fileOut.println("0");
        }

        // Close the PrintWriter to flush/write anything not already written to the file.
        fileOut.close();
    }


    /**
     *
     * @param files FileInOut object to be used for I/O.
     * @param d First RSA key.
     * @param n Second RSA key.
     */
    public static void decrypt(FileInOut files, int d, int n) {

        // List of Strings, each representing
        List<String> input = getFileContents(files.getInFile());

        //
        List<String> currentBuffer = new ArrayList<String>();
        //
        List<String[]> buffers = new ArrayList<String[]>();


        // For each line in the input
        for(String line : input) {
            //
            if(line.equals("0")) {
                buffers.add((String[])currentBuffer.toArray());
                currentBuffer = new ArrayList<String>();
            } else {
                //
                currentBuffer.add(line);
            }
        }

        for(String[] buffer : buffers) {

        }
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


    /**
     *
     */
    private Rsa() {
        throw new AssertionError("Class Rsa not meant to be instantiated!");
    }
}
