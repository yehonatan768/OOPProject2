import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileLineCounterThread extends Thread {
    private String fileName;
    private int numLines;

    public FileLineCounterThread(String fileName) {
        this.fileName = fileName;
    }

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

    public int getNumLines() {
        return numLines;
    }
}