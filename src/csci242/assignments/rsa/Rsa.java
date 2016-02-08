package csci242.assignments.rsa;


import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Project Assignments.
 * Created by breaker on 2/3/16.
 */


public class Rsa {

    /**
     * @param c Character to convert to int.
     * @return The associated int to c.
     */
    private int translateChar(char c) {
        return c - 'A' + 1;
    }

//    public void encrypt(FileInOut files, int e, int n) {
//        String input  = "";
//        String output = "";
//
//        Scanner     fileIn  = files.getInFile();
//        PrintWriter fileOut = files.getOutFile();
//
//        int   arrSize;
//        int[] cOfI;
//
//        while(fileIn.hasNextLine())
//            input += fileIn.nextLine() + '\n';
//
//        arrSize = input.length();
//
//        cOfI = new int[arrSize];
//        for(int i = 0; i < arrSize; i++) {
//            if(input.charAt(i) == '\n')
//                cOfI[i] = 0;
//            else
//                cOfI[i] = mod(e, n, input.charAt(i));
//        }
//
//        for(int a : cOfI) {
//            if(a == 0)
//                output += "0\n";
//            else
//                output += String.format("%d\n", a);
//        }
//
//        fileOut.write(output);
//    }

    public void encrypt(FileInOut files, int e, int n) {
        String input  = "";
        String output = "";

        int      arrSize;
        char[][] pairs;
        int[]    cOfI;
        int numberOfLines = 0;
        int[][] indexesOfNewLines;

        Scanner     fileIn  = files.getInFile();
        PrintWriter fileOut = files.getOutFile();

        while(fileIn.hasNextLine()) {
            input += fileIn.nextLine();
            numberOfLines += 1;
        }

        arrSize = (input.length() / 2;
        // Make arrSize even if it isn't already
        if(arrSize % 2 != 0)
            arrSize += 1;


        pairs = new char[arrSize][2];
        for(int i = 0, a = 0, b = 0; i < input.length() && a < arrSize; i++) {
            char c = input.charAt(i);
            if(c == '0') {
                pairs[a][b] = '0';
            } else {
                pairs[a][b] = c;
            }

            // Setup for the next iteration
            if(i % 2 == 0) {
                a += 1;
                b = 1;
            } else {
                b = 0;
            }
        }

        // Even out the number of characters
        if(pairs[arrSize-1][1] == 0) {
            pairs[arrSize-1][1] = 'X';
        }


        cOfI = new int[arrSize];
        for(int i = 0; i < cOfI.length; i++) {
            if(pairs[i][0] == '0') {

            }
            cOfI[i] = mod(e, n, translateChar(pairs[i][0]) * 100 + translateChar(pairs[i][1]));
        }


        for(int c : cOfI)
            output += String.format("%d\n", c);


        System.out.println(output);
    }

    private int mod(int e, int n, int p) {
        int result = 1;
        for(int j = 0; j < e; j++)
            result = (result * p) % n;

        return result;
    }

    public static void main(String[] args) throws IOException {
        TemporaryFolder tmp = new TemporaryFolder();
        tmp.create();
        File input;
        File output;

        input = tmp.newFile();
        output = tmp.newFile();

        PrintWriter writer = new PrintWriter(input);
        writer.write("IDESOFMARCH");
        writer.close();

        FileInOut fio = new FileInOut(input.getAbsolutePath(), output.getAbsolutePath(), true);
        Rsa rsa = new Rsa();

        rsa.encrypt(fio, 17, 2773);
    }

    public void decrypt(FileInOut files, int d, int n) {

        String input = "";
        while(files.getInFile().hasNextLine())
            input += files.getInFile().nextLine();
    }
}


class CharacterPair {

    public final char first, second;

    CharacterPair(char first, char second) {
        this.first = first;
        this.second = second;
    }
}
