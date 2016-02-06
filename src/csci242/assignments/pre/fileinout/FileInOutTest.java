package csci242.assignments.pre.fileinout;


import org.junit.*;
import org.junit.rules.*;
import static org.junit.Assert.*;

import java.io.*;


/**
 * Project Assignments.
 * Created on 2/3/16.
 *
 * @author breaker
 */


public class FileInOutTest {

    @ClassRule
    public static TemporaryFolder tmp = new TemporaryFolder();

    public static File inFile;
    public static File outFile;

    @Rule
    public final ExpectedException fileNotFound = ExpectedException.none();

    @BeforeClass
    public static void setupFiles() throws IOException {

        inFile  = tmp.newFile();
        outFile = tmp.newFile();

        String inFileText  = "Hello, world!\n";
        String outFileText = "Goodbye, world!\n";

        PrintWriter writer = new PrintWriter(inFile);
        writer.write(inFileText);

        writer = new PrintWriter(outFile);
        writer.write(outFileText);
    }

    @Test
    public void testSettersAndGetters() throws Exception {

        FileInOut instance = new FileInOut("in.txt", "out.txt", false);

        instance.setInFilename(inFile.getName());
        instance.setOutFilename(outFile.getName());

        assertEquals(inFile.getName(), instance.getInFilename());
        assertEquals(outFile.getName(), instance.getOutFilename());
    }

    @Test
    public void testFileOpeningAndClosing() throws Exception {

        FileInOut instance = new FileInOut(inFile.getAbsolutePath(), outFile.getAbsolutePath(), true);

        assertNotNull(instance.getInFile());
        assertNotNull(instance.getOutFile());

        assertTrue(instance.inFileIsOpen());
        assertTrue(instance.outFileIsOpen());

        // Close files
        instance.closeFiles();

        // Assert inFileIsOpen() returns false after closing files
        assertFalse(instance.inFileIsOpen());
        assertFalse(instance.outFileIsOpen());
    }

    @Test
    public void testInFileNotFound() throws Exception {
        fileNotFound.expect(FileNotFoundException.class);
        new FileInOut("", outFile.getAbsolutePath(), true);
    }
}
