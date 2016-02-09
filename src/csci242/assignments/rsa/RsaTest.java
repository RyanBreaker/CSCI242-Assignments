package csci242.assignments.rsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Project Assignments.
 * Created on 2/8/16.
 * @author Ryan Breaker
 */

public class RsaTest {

    TemporaryFolder tmp = new TemporaryFolder();
    File input, output;
    FileInOut fio;
    PrintWriter inputWriter;
    Scanner outputScanner;

    Rsa rsa = new Rsa();

    @Before
    public void setUp() throws Exception {
        input  = tmp.newFile();
        output = tmp.newFile();
        fio = new FileInOut(input.getAbsolutePath(), output.getAbsolutePath(), false);

        inputWriter   = new PrintWriter(input);
        outputScanner = new Scanner(output);
    }

    @After
    public void tearDown() throws Exception {
        // Close all files after each test.
        fio.closeFiles();
        inputWriter.close();
    }

    @Test
    public void testEncrypt() throws Exception {
        // Write test text and write/close the file
        inputWriter.println("IDESOFMARCH");
        inputWriter.close();

        Rsa.encrypt(fio, 17, 2773);

        String outputFile = "";
        while(outputScanner.hasNextLine())
            outputFile += outputScanner.nextLine();

        System.out.println(outputFile);
    }

    @Test
    public void testDecrypt() throws Exception {

    }
}