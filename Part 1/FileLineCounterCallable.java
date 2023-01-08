import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * A class that implements the Callable interface to count the number of lines in a text file.
 *
 * @author  [Your Name]
 * @version [Insert Version Number]
 * @since   [Insert Date]
 */
public class FileLineCounterCallable implements Callable<Integer> {
    private String fileName;
    
    /**
 * Constructs a FileLineCounterCallable object.
 *
 * @param fileName The name of the file to count the lines of.
 */
    public FileLineCounterCallable(String fileName) {
        this.fileName = fileName;
    }
    
    /**
 * Counts the number of lines in a text file.
 *
 * @return The number of lines in the file.
 * @throws Exception If an error occurs while reading the file.
 */
    @Override
    public Integer call() {
        int numLines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                numLines++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + fileName);
            e.printStackTrace();
        }
        return numLines;
    }
}

