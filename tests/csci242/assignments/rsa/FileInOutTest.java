package csci242.assignments.rsa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A program to encrypt and decrypt a file using the RSA encryption algorithm.
 * <p>
 * These are the tests for the FileInOut class.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 1
 * @bugs None
 */
public class FileInOutTest {

    FileInOut fio;

    TemporaryFolder tmp;
    File original;
    File encrypted;
    File decrypted;

    final String testString = "HELLO\nFOO\nILIKEPIE\n";

    final int e = 1007;
    final int d = 975;
    final int n = 2773;

    // Only a single instance of Rsa is ever needed for testing
    final Rsa rsa = new Rsa();

    @Before
    public void setUp() throws Exception {
        PrintWriter writer;

        tmp = new TemporaryFolder();
        tmp.create();

        original = tmp.newFile();
        encrypted = tmp.newFile();
        decrypted = tmp.newFile();

        writer = new PrintWriter(original);
        writer.write(testString);
        writer.close();

        fio = new FileInOut(original.getAbsolutePath(),
                encrypted.getAbsolutePath(), true);
    }

    @After
    public void tearDown() throws Exception {
        // Close all the files...
        fio.closeFiles();
        // And delete the evidence.
        tmp.delete();
    }


    @Test
    public void testGettersSetters() throws Exception {
        String newIn = "in.txt";
        String newOut = "out.txt";

        String blank = "";

        String currentInFilename = fio.getInFilename();
        String currentOutFilename = fio.getOutFilename();

        assertEquals(currentInFilename, original.getAbsolutePath());
        assertEquals(currentOutFilename, encrypted.getAbsolutePath());

        fio.setInFilename(newIn);
        fio.setOutFilename(newOut);

        assertEquals(newIn, fio.getInFilename());
        assertEquals(newOut, fio.getOutFilename());

        fio.setOutFilename(blank);

        assertEquals(newIn, fio.getInFilename());
        assertEquals(blank, fio.getOutFilename());
    }

    @Test
    public void testBooleanGetters() throws Exception {
        assertTrue(fio.inFileIsOpen());
        assertTrue(fio.outFileIsOpen());

        fio.closeFiles();

        assertFalse(fio.inFileIsOpen());
        assertFalse(fio.outFileIsOpen());

        fio.openFiles();

        assertTrue(fio.inFileIsOpen());
        assertTrue(fio.outFileIsOpen());
    }

    @Test
    public void testCheckFilesMethod() throws Exception {
        fio.closeFiles();

        assertFalse(fio.inFileIsOpen());
        assertFalse(fio.outFileIsOpen());

        fio.checkFiles();

        assertTrue(fio.inFileIsOpen());
        assertTrue(fio.outFileIsOpen());
    }
}
