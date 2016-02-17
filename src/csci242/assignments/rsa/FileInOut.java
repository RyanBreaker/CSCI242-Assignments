package csci242.assignments.rsa;

import java.io.*;
import java.util.*;

// TODO: Double-check exception throwing

/**
 * A program to encrypt and decrypt a file using the RSA encryption algorithm.
 * <p>
 * FileInOut holds and keeps track of a Scanner for an input file and a
 * PrintWriter for an output file, used primarilyfor reading input and writing
 * output for the Rsa class.
 * <p>
 * Each time a file is opened, a new Object of the type associated with the file
 * (Scanner for input, PrintWriter for output) is instantiated and assigned to
 * its relative property of this.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 1
 * @bugs None
 */
public class FileInOut {

    /**
     * Default filename for the input file.
     */
    private final String DEFAULTINFILENAME  = "default_in.txt";
    /**
     * Default filename for the output file.
     */
    private final String DEFAULTOUTFILENAME = "default_out.txt";

    /**
     * The current filename of the input file.
     */
    private String inFilename;
    /**
     * The current filename of the output file.
     */
    private String outFilename;

    /**
     * Represents whether the input file's Scanner is open or not.
     */
    private boolean inFileOpen = false;
    /**
     * Represents whether the output file's PrintWriter is open or not.
     */
    private boolean outFileOpen = false;

    /**
     * The Scanner for the input file for reading.
     */
    private Scanner inFileScanner;
    /**
     * The PrintWriter for the output file for writing.
     */
    private PrintWriter outFileWriter;


    /**
     * Constructs the object giving the initial input and output filenames.
     * If pOpenFlag is given as true, the constructor will call the meta
     * method openFiles() automatically.
     * <p>
     * If a given filename's String is empty, a default of "default_in.txt"
     * or "default_out.txt" is used instead.
     *
     * @param  pIn   Name of the input file.
     * @param  pOut  Name of the output file.
     * @param  pOpenFlag
     *         If true, files will be opened automatically, otherwise
     *         the files will need to be opened manually.
     *
     * @throws FileNotFoundException
     *         Thrown if the input file does not exist or cannot be written
     *         or if the output cannot be written to.
     *
     */
    public FileInOut(String pIn, String pOut, boolean pOpenFlag)
            throws FileNotFoundException {

        inFilename  = pIn;
        outFilename = pOut;

        if(pOpenFlag) {
            openFiles();
        }
    }


    //region Getters and Setters
    /**
     * Returns the current filename of the input file.
     *
     * @return  The current filename of the input file.
     */
    public String getInFilename()  { return inFilename; }

    /**
     * Returns the current filename of the output file.
     *
     * @return  The current filename of the output file.
     */
    public String getOutFilename() { return outFilename; }

    /**
     * Sets the input file's filename to the given String.
     *
     * @param  inFilename  String to set the input file's filename to.
     */
    public void setInFilename(String inFilename) {
        this.inFilename = inFilename;
    }

    /**
     * Sets the output file's filename to the given String. This does NOT
     * change the PrintWriter's target for writing!!! Manual refreshing
     * of this object will be required with closeOutFile() and openOutFile(),
     * or with the closeFiles() and openFiles() meta methods for both.
     *
     * @param  outFilename  String to set the output file's filename to.
     */
    public void setOutFilename(String outFilename) {
        this.outFilename = outFilename;
    }

    /**
     * Returns the Scanner for reading the input file. This does NOT
     * change the Scanners target file for reading!!! Manual refreshing
     * of this object will be required with closeInFile() and openInFile(),
     * or with the closeFiles() and openFiles() meta methods for both.
     *
     * @return  The Scanner for reading the input file.
     */
    public Scanner getInFile() { return inFileScanner; }

    /**
     * Returns the PrintWriter for writing to the output file.
     *
     * @return  The PrintWriter for writing to the output file.
     */
    public PrintWriter getOutFile() { return outFileWriter; }
    //endregion

    /**
     * Returns true if the input file's Scanner is open, false otherwise.
     *
     * @return  True if the input file's Scanner is open, false otherwise.
     */
    public boolean inFileIsOpen()  { return inFileOpen;  }

    /**
     * Returns true if the input file's PrintWriter is open, false otherwise.
     *
     * @return  True if the input file's PrintWriter is open, false otherwise.
     */
    public boolean outFileIsOpen() { return outFileOpen; }


    //region Open methods
    /**
     * Meta method for opening both files.
     *
     * @throws  FileNotFoundException
     *          Thrown on nonexistant input file or output file is unwriteable.
     */
    public void openFiles() throws FileNotFoundException {
        openInFile();
        openOutFile();
    }

    /**
     * Opens a Scanner for the input file.
     * <p>
     * Checks if inFilenam has content (is not an empty string), otherwise
     * DEFAULTINFILENAME ("default_in.txt") is used as the filename.
     *
     * @throws  FileNotFoundException
     *          If the input file does not exist or cannot be read.
     */
    public void openInFile() throws FileNotFoundException {
        File inFile;

        if(inFilename.length() > 0) {
            inFile = new File(inFilename);
        } else {
            inFile = new File(DEFAULTINFILENAME);
        }

        // Close the Scanner if it's already open before assigning new object.
        if(inFileOpen) {
            inFileScanner.close();
        }

        try {
            inFileScanner = new Scanner(inFile);
        } catch(FileNotFoundException e) {
            String message = "Input file '" + inFile.getAbsolutePath() +
                    "' cannot be opened!\n" + e.getMessage();
            throw new FileNotFoundException(message);
        }

        inFileOpen = true;
    }

    /**
     * Opens outFile's PrintWriter.
     * <p>
     * Checks if outFilename has content (is not an empty string),
     * otherwise DEFAULTOUTFILENAME is used as the filename.
     *
     * @throws  FileNotFoundException
     *          If the output cannot be written to.
     */
    public void openOutFile() throws FileNotFoundException {
        File outFile;

        if(outFilename.length() > 0) {
            outFile = new File(outFilename);
        } else {
            outFile = new File(DEFAULTOUTFILENAME);
        }

        // Close the Writer if it's already open before assigning new object.
        if(outFileOpen) {
            outFileWriter.close();
        }

        try {
            outFileWriter = new PrintWriter(outFile);
        } catch(FileNotFoundException e) {
            String message = "Output file '" + outFile.getAbsolutePath() +
                    "' cannot be opened!\n" + e.getMessage();
            throw new FileNotFoundException(message);
        }

        outFileOpen = true;
    }
    //endregion

    //region Close methods
    /**
     * Meta method for closing all files.
     */
    public void closeFiles() {
        closeInFile();
        closeOutFile();
    }

    /**
     * Closes the input file's Scanner.
     */
    public void closeInFile() {
        inFileScanner.close();
        inFileOpen = false;
    }

    /**
     * Closes the output file's PrintWriter,
     * flushing any remaining unwritten output.
     */
    public void closeOutFile() {
        outFileWriter.close();
        outFileOpen = false;
    }
    //endregion

    /**
     * Checks the opened status of each the input and output files and
     * opens them if either is closed.
     *
     * @throws FileNotFoundException
     *         Thrown if the input file does not exist or cannot
     *         be read or the output file cannot be written to.
     */
    public void checkFiles() throws FileNotFoundException {
        if(!inFileOpen) {
            openInFile();
        }

        if(!outFileOpen) {
            openOutFile();
        }
    }
}
