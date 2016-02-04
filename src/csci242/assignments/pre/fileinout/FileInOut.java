package csci242.assignments.pre.fileinout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Project Assignments.
 * Created on 2/3/16.
 *
 * @author breaker
 */


public class FileInOut {

    private String inFilename;
    private String outFilename;

    private Scanner inFileScanner;
    private PrintWriter outFileWriter;


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
    public String getInFilename() {
        return inFilename;
    }

    /**
     * @param inFilename String to set the input filename to.
     */
    public void setInFilename(String inFilename) {
        this.inFilename = inFilename;
    }


    /**
     * @return Filename of the file to be written.
     */
    public String getOutFilename() {
        return outFilename;
    }


    /**
     * @param outFilename String to set the output filename to.
     */
    public void setOutFilename(String outFilename) {
        this.outFilename = outFilename;
    }

    public Scanner getInFile() {
        return this.inFileScanner;
    }

    public PrintWriter getOutFile() {
        return this.outFileWriter;
    }
    //endregion

    //region Open methods
    public void openFiles() throws FileNotFoundException {
        openInFile();
        openOutFile();
    }

    public void openInFile() throws FileNotFoundException {
        File inFile = new File(inFilename);
        inFileScanner = new Scanner(inFile);

        // Check for content
        if(!inFileScanner.hasNext()) {
            System.err.println(String.format("Error: File %s is empty!", inFilename));
        }
    }

    public void openOutFile() throws FileNotFoundException {
        File outFile = new File(outFilename);
        outFileWriter = new PrintWriter(outFile);
    }
    //endregion

    //region Close methods
    public void closeFiles() {
        closeInFile();
        closeOutFile();
    }

    public void closeInFile() {
        inFileScanner.close();
    }

    public void closeOutFile() {
        outFileWriter.close();
    }
    //endregion
}
