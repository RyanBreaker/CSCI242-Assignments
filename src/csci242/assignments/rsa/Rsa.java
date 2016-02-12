package csci242.assignments.rsa;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
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
    public void encrypt(FileInOut files, int e, int n) {

        // PrintWriter for writing to the output file.
        PrintWriter fileOut = files.getOutFile();

        // For each line in input, grab each pair, encrypt its ASCII sum, and write it to the output.
        for(String line : getFileContents(files.getInFile())) {
            // Even-out the line's length if currently odd-lengthed by appending 'X'.
            if(line.length() % 2 != 0) {
                line += "X";
            }

            // Divide the line into 2-character pieces, then translate, combine, encrypt, and write them.
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
    public void decrypt(FileInOut files, int d, int n) {

        //
        PrintWriter fileOut = files.getOutFile();

        // For each line in the input,
        for(String line : getFileContents(files.getInFile())) {
            int m = 0;

            // If the line is just "0", it's a new line in the original file.
            if(line.equals("0")) {
                fileOut.println();
                // Skip to next iteration
                continue;
            }

            //
            try {
                m = mod(d, n, Integer.parseInt(line));
            } catch(NumberFormatException e) {
                e.printStackTrace();
            }

            //
            Character firstChar  = translateCharReverse(m / 100);
            Character secondChar = translateCharReverse(m % 100);

            //
            fileOut.print(firstChar.toString() + secondChar.toString());
        }

        //
        fileOut.close();
    }


    /**
     * Translates the given char down such that the numeric value of ASCII 'A' is the origin at 0.
     * @param c The char to translate.
     * @return The translated int from c.
     */
    private int translateChar(char c) {
        return c - 'A';
    }

    /**
     * Reverses the effect of translateChar, translating n up and returning a char.
     * @param n The number to translate.
     * @return The translated char from n.
     */
    private char translateCharReverse(int n) {
        return (char)(n + 'A');
    }


    /**
     * Modulation algorithm for easier handling of massive, exponential numbers.
     * @param e First RSA key
     * @param n Second RSA key
     * @param p Pair's numeric ASCII sum (with 'A' subtracted)
     * @return Returns C = P^e % n
     */
    private int mod(int e, int n, int p) {
        int result = 1;
        for(int j = 0; j < e; j++) {
            result = (result * p) % n;
        }

        return result;
    }


    /**
     * 
     * @param fileIn Scanner for reading input file.
     * @return Returns List of Strings, each element representing each line in the file.
     */
    private List<String> getFileContents(Scanner fileIn) {
        List<String> fileContents = new ArrayList<String>();

        while(fileIn.hasNextLine()) {
            fileContents.add(fileIn.nextLine());
        }

        return fileContents;
    }
}
