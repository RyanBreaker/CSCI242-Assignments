package csci242.assignments.rsa;


import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Project Assignments.
 * Created by breaker on 2/3/16.
 */


public class Rsa {

    /**
     * @param c Character to convert to int.
     * @return The associated int to c.
     */
    private int charToInt(char c) {
        return c - 'A';
    }

    public void encrypt(FileInOut files, int e, int n) {
        String input = "";
        String output = "";

        while(files.getInFile().hasNextLine()) {
            input += files.getInFile().nextLine();
        }

        int arrSize = input.length() / 2;
        // Make arrSize even if it isn't already
        if(arrSize % 2 != 0)
            arrSize += 1;

        String[] inputSplit = new String[arrSize];

        int[] pOfI = new int[arrSize];
        for(int i = 0, a = 0; i < input.length(); i += 2) {
            int first = charToInt(input.charAt(i));
            int second = charToInt(input.charAt(i+1));

            pOfI[a++] = Integer.parseInt(String.format("%d%02d", first, second));
        }

        for(int p : pOfI) {
            output += String.format("%d ", mod(e, n, p));
        }

//        int[][] mOfI = new int[arrSize][2];
//        for(int i = 0, a = 0; i < input.length(); i++) {
//            // Add the character to the array
//            mOfI[a][i%2] = charToInt(input.charAt(i));
//
//            // Increment array index every other item
//            if((i + 1) % 2 == 0)
//                a++;
//        }
//
//        for(int[] a : mOfI) {
//            int p = Integer.parseInt(String.format("%02d%02d", a[0], a[1]));
//            int c = mod(e, n, p);
//            output += String.format("%d ", c);
//        }

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

    }
}
