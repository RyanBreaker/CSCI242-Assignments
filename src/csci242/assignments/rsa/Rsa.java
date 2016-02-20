package csci242.assignments.rsa;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * A program to encrypt and decrypt a file using the RSA encryption algorithm.
 * <p>
 * The Rsa class implements the functionality of the RSA algirithm.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 1
 * @bugs On bad input, a NumberFormatException will be thrown in Rsa::decrypt().
 */
public class Rsa {

    /**
     * Encrypts the inFile from the given FileInOut with
     * the given keys and writes it to the outFile.
     *
     * @param  files  FileInOut object to be used.
     * @param  e  First RSA key as an int.
     * @param  n  Second RSA key as an int.
     */
    public void encrypt(FileInOut files, int e, int n)
            throws FileNotFoundException {

        // PrintWriter for writing to the output file.
        PrintWriter fileOut;

        checkFiles(files);

        fileOut = files.getOutFile();

        // For each line in input, grab each pair, encrypt
        // its ASCII sum and write it to the output.
        for(String line : getFileContents(files.getInFile())) {

            // Even-out the line's length if currently
            // odd-lengthed by appending 'X'.
            if(line.length() % 2 != 0) {
                line += "X";
            }

            // Divide the line into 2-character pieces, then
            // translate, combine, encrypt, and write them.
            for(int i = 0, c; i < line.length(); i += 2) {
                c = translateChar(line.charAt(i)) * 100
                        + translateChar(line.charAt(i+1));
                fileOut.println(mod(e, n, c));
            }

            // '0' to represent new line in original file.
            fileOut.println("0");
        }

        // Close both files, effectively flushing remaining ouput.
        files.closeFiles();
    }


    /**
     * Decrypts the inFile from the given FileInOut with
     * the given keys and writes it the the outFile.
     * <p>
     * If either the input or output are closed, files.openFiles()
     * gets called which may throw a FileNotFoundException if
     * either the input does not exist or cannot be read or the
     * output cannot be written to.
     *
     * @param  files  FileInOut object to be used for I/O.
     * @param  d  First RSA key.
     * @param  n  Second RSA key.
     * @throws  FileNotFoundException
     *          Thrown if the input does not exist or cannot be read or ouput
     *          cannot be written.
     */
    public void decrypt(FileInOut files, int d, int n)
            throws FileNotFoundException {

        PrintWriter fileOut;

        // Potential FileNotFoundException
        checkFiles(files);

        fileOut = files.getOutFile();

        // For each line in the input,
        for(String line : getFileContents(files.getInFile())) {
            int m;
            Character firstChar;
            Character secondChar;

            // If the line is just "0", it's a new line in the original file.
            if(line.equals("0")) {
                // Write a new line.
                fileOut.println();
                // Skip to next iteration
                continue;
            }

            // Warning: May throw NumberFormatException on bad input
            m = mod(d, n, Integer.parseInt(line));

            // Get each character from the parsed input
            firstChar  = translateCharReverse(m / 100);
            secondChar = translateCharReverse(m % 100);

            //
            fileOut.print(firstChar.toString() + secondChar.toString());
        }

        // Close both files, effectively flushing remaining ouput.
        files.closeFiles();
    }


    /**
     * Checks the opened status of each the input and output files and
     * opens them if either is closed.
     *
     * @param  files  The FileInOut object to check.
     *
     * @throws FileNotFoundException
     *         Thrown if the input file does not exist or cannot
     *         be read or the output file cannot be written to.
     */
    private void checkFiles(FileInOut files) throws FileNotFoundException {
        if(!files.inFileIsOpen()) {
            files.openInFile();
        }

        if(!files.outFileIsOpen()) {
            files.openOutFile();
        }
    }


    /**
     * Translates the given char down such that the numeric value of
     * ASCII 'A' is the origin at 0.
     *
     * @param  c  The char to translate.
     *
     * @return  The translated int from c.
     */
    private int translateChar(char c) {
        return c - 'A' + 1;
    }

    /**
     * Reverses the effect of translateChar, translating n up and
     * returning a char.
     *
     * @param  n  The number to translate.
     *
     * @return  The translated char from n.
     */
    private char translateCharReverse(int n) {
        return (char)(n + 'A' - 1);
    }


    /**
     * Modulation algorithm for easier handling of massive, exponential numbers.
     *
     * @param  e  First RSA key
     * @param  n  Second RSA key
     * @param  p  Pair's numeric ASCII sum (with 'A' subtracted)
     *
     * @return  C = P^e % n
     */
    private int mod(int e, int n, int p) {
        int result = 1;
        for(int j = 0; j < e; j++) {
            result = (result * p) % n;
        }

        return result;
    }


    /**
     * Generates a List
     *
     * @param  fileIn  Scanner for reading input file.
     *
     * @return  A List of Strings, each element representing each line in
     *          the file.
     */
    private List<String> getFileContents(Scanner fileIn) {
        List<String> fileContents = new ArrayList<String>();

        while(fileIn.hasNextLine()) {
            fileContents.add(fileIn.nextLine());
        }

        return fileContents;
    }
}
