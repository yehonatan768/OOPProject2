import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class that extends the Thread class to count the number of lines in a text file.
 * @author Noy Dayan, Yehonatan Mekayten.
 * @version 1.
 */
public class FileLineCounterThread extends Thread {
    private String fileName;
    private int numLines;
    
    /**
 * Constructs a FileLineCounterThread object.
 *
 * @param fileName The name of the file to count the lines of.
 */
    public FileLineCounterThread(String fileName) {
        this.fileName = fileName;
    }
    
    /**
 * Counts the number of lines in a text file.
 */

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                numLines++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + fileName);
            e.printStackTrace();
        }
    }
    
    /**
 * Returns the number of lines in the file.
 *
 * @return The number of lines in the file.
 */
    public int getNumLines() {
        return numLines;
    }
}
