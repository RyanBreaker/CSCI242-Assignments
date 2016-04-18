package csci242.assignments.rsa;

import csci242.assignments.rsa.FileInOut;
import csci242.assignments.rsa.Rsa;
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
 *
 * @author Ryan Breaker
 */
public class RsaTest {

    final String testString = "HELLO\nFOOBAR\nILIKEPIE\n";
    final String testDecrypted = "HELLOX\nFOOBAR\nILIKEPIE\n";
    final int e = 1007;
    final int d = 975;
    final int n = 2773;
    // Only a single instance is needed for any test.
    final Rsa rsa = new Rsa();
    TemporaryFolder tmp = new TemporaryFolder();
    File original;
    File encrypted;
    File decrypted;
    FileInOut fio;

    @Before
    public void setUp() throws Exception {
        PrintWriter writer;

        tmp.create();

        original = tmp.newFile();
        encrypted = tmp.newFile();
        decrypted = tmp.newFile();

        // Write test text to original file
        writer = new PrintWriter(original);
        writer.write(testString);
        writer.close();

        fio = new FileInOut(original.getAbsolutePath(),
                encrypted.getAbsolutePath(), true);
    }

    @After
    public void tearDown() throws Exception {
        fio.closeFiles();
        tmp.delete();
    }


    @Test
    public void testEncryptDecrypt() throws Exception {
        Scanner reader;
        String actualDecryptedText = "";

        rsa.encrypt(fio, e, n);

        fio.closeFiles();

        fio.setInFilename(encrypted.getAbsolutePath());
        fio.setOutFilename(decrypted.getAbsolutePath());

        fio.openFiles();

        rsa.decrypt(fio, d, n);

        reader = new Scanner(decrypted);
        while (reader.hasNextLine()) {
            actualDecryptedText += reader.nextLine() + "\n";
        }

        assertEquals(testDecrypted, actualDecryptedText);
    }
}
