import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;


public class FileLineCounterCallable implements Callable<Integer> {
    private String fileName;

    public FileLineCounterCallable(String fileName) {
        this.fileName = fileName;
    }

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

