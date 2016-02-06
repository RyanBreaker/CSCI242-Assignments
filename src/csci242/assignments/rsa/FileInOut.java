package csci242.assignments.rsa;

import java.io.*;
import java.util.*;

/**
 * Project Assignments.
 * Created on 2/3/16.
 *
 * @author breaker
 */


public class FileInOut {

    private String inFilename;
    private String outFilename;

    private boolean inOpen  = false;
    private boolean outOpen = false;

    private Scanner inFileScanner;
    private PrintWriter outFileWriter;


    /**
     * @param pIn Name of the input file.
     * @param pOut Name of the output file.
     * @param pOpenFlag If true, will open files automatically, otherwise manual opening will be needed.
     * @throws FileNotFoundException Thrown on nonexistant file or file is unwriteable.
     */
    public FileInOut(String pIn, String pOut, boolean pOpenFlag) throws FileNotFoundException {
        inFilename  = pIn;
        outFilename = pOut;

        if(pOpenFlag) {
            openFiles();
        }
    }


    //region Getters and Setters
    /**
     * @return Filename of the file to be read.
     */
    public String getInFilename()  { return inFilename; }

    /**
     * @return Filename of the file to be written.
     */
    public String getOutFilename() { return outFilename; }

    /**
     * @param inFilename String to set the input filename to.
     */
    public void setInFilename(String inFilename) {
        this.inFilename = inFilename;
    }

    /**
     * @param outFilename String to set the output filename to.
     */
    public void setOutFilename(String outFilename) {
        this.outFilename = outFilename;
    }

    /**
     * @return The Scanner that reads inFile.
     */
    public Scanner getInFile()      { return inFileScanner; }

    /**
     * @return The PrintWriter that writes outFile.
     */
    public PrintWriter getOutFile() { return outFileWriter; }
    //endregion

    /**
     * @return Returns true if the input file's Scanner is open, false otherwise.
     */
    public boolean inFileIsOpen()  { return inOpen;  }

    /**
     * @return Returns true if the output file's PrintWriter is open, false otherwise.
     */
    public boolean outFileIsOpen() { return outOpen; }


    //region Open methods
    /**
     * Meta method for opening both files.
     * @throws FileNotFoundException Thrown on nonexistant file or file is unwriteable.
     */
    public void openFiles() throws FileNotFoundException {
        openInFile();
        openOutFile();
    }

    /**
     * Opens inFile's Scanner.
     * @throws FileNotFoundException
     */
    public void openInFile() throws FileNotFoundException {
        if(inFilename.length() == 0) {
            throw new FileNotFoundException("inFilename is empty.");
        }

        File inFile   = new File(inFilename);
        inFileScanner = new Scanner(inFile);
        inOpen = true;
    }

    /**
     * Opens outFile's PrintWriter.
     * @throws FileNotFoundException
     */
    public void openOutFile() throws FileNotFoundException {
        if(outFilename.length() == 0) {
            throw new FileNotFoundException("outFilename is empty.");
        }

        File outFile  = new File(outFilename);
        outFileWriter = new PrintWriter(outFile);
        outOpen = true;
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
        inOpen = false;
    }

    /**
     * Closes the output file's PrintWriter.
     */
    public void closeOutFile() {
        outFileWriter.close();
        outOpen = false;
    }
    //endregion
}
