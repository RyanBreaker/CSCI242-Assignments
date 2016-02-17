package csci242.assignments.rsa;

/**
 * A program to encrypt and decrypt a file using the RSA encryption algorithm.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 1
 * @bugs None
 */
public class RsaDriver {

    public static void main(String[] args) throws Exception {

        final int e = 1007;
        final int d = 975;
        final int n = 2773;

        final String plainTextFilename     = "testFiles/file.txt";
        final String encryptedTextFilename = "testFiles/encryptedFile.txt";
        final String decryptedTextFilename = "testFiles/decryptedFile.txt";

        FileInOut fio = new FileInOut(plainTextFilename,
                                      encryptedTextFilename, true);
        Rsa rsa = new Rsa();

        // Encrypt plainTextFilename contents
        rsa.encrypt(fio, e, n);

        fio.setInFilename(encryptedTextFilename);
        fio.setOutFilename(decryptedTextFilename);

        // Decrypt encryptedTextFilename contents
        rsa.decrypt(fio, d, n);
    }
}
