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
    private int translateChar(char c) {
        return c - 'A';
    }

    public void encrypt(FileInOut files, int e, int n) {
        String input = "";
        String output = "";

        int arrSize;
        char[][] pairs;
        int[] cOfI;

        while(files.getInFile().hasNextLine()) {
            input += files.getInFile().nextLine();
        }


        arrSize = input.length() / 2;
        // Make arrSize even if it isn't already
        if(arrSize % 2 != 0)
            arrSize += 1;


        pairs = new char[arrSize][2];
        for(int i = 0, a = 0; i < input.length() && a < arrSize; i++) {
            if(i % 2 == 0)
                pairs[a][0] = input.charAt(i);
            else
                pairs[a++][1] = input.charAt(i);
        }


        cOfI = new int[arrSize];
        for(int i = 0; i < cOfI.length; i++) {
            cOfI[i] = mod(e, n, translateChar(pairs[i][0]) * 100 + translateChar(pairs[i][1]));
        }


        for(int c : cOfI)
            output += String.format("%d ", c);


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
