package csci242.assignments.facebooklite;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Short description.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 5
 * @bugs None
 */
public class CommandParserTest {
    private CommandParser testParser;
    private String testLine;
    private Command testCommand;

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();
    private File tmpFile;
    private PrintWriter writer;

    private void write(String s) {
        writer.write(s);
        writer.close();
    }

    @Before
    public void setUp() throws IOException {
        tmpFile = tmp.newFile();
        writer = new PrintWriter(tmpFile);
    }
}
