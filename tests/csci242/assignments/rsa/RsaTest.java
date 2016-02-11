package csci242.assignments.rsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


/**
 * Project Assignments.
 * Created on 2/8/16.
 * @author Ryan Breaker
 */
public class RsaTest {

    final TemporaryFolder tmp = new TemporaryFolder();
    File input, output;
    FileInOut fio;
    PrintWriter inputWriter;
    Scanner outputScanner;

    final Rsa rsa = new Rsa();


    @Before
    public void setUp() throws Exception {
        tmp.create();
        input  = tmp.newFile();
        output = tmp.newFile();
        fio = new FileInOut(input.getAbsolutePath(), output.getAbsolutePath(), true);

        inputWriter   = new PrintWriter(input);
        outputScanner = new Scanner(output);
    }

    @After
    public void tearDown() throws Exception {
        // Close all files after each test.
        fio.closeFiles();
        inputWriter.close();
        tmp.delete();
    }

    @Test
    public void testEncryptDecrypt() throws Exception {
        // Write test text and write/close the file
        inputWriter.println("IDESOFMARCH");
        inputWriter.close();

        rsa.encrypt(fio, 17, 2773);

        final String expectedOutput = "779\n1983\n2641\n1444\n52\n802\n0\n";

        String actualOutput = "";
        while(outputScanner.hasNextLine()) {
            actualOutput += outputScanner.nextLine() + "\n";
        }

        assertEquals("Actual output doesn't match expected output!", expectedOutput, actualOutput);

        fio = new FileInOut(output.getAbsolutePath(), input.getAbsolutePath(), true);

        rsa.decrypt(fio, 157, 2773);

        Scanner scanner = new Scanner(input);
        while(scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }
}
