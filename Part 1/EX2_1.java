import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * A class for counting the number of lines in a given set of text files. The count can be performed using
 * multiple threads, a thread pool, or a single thread.
 *
 * @author  [Your Name]
 * @version [Insert Version Number]
 * @since   [Insert Date]
 */
public class EX2_1 {
    
 /**
 * Creates a specified number of text files with a given number of lines.
 *
 * @param n      The number of files to create.
 * @param seed   The seed for the random number generator.
 * @param bound  The upper bound for the number of lines in each file.
 * @return       An array of file names.
 * @throws IOException If an error occurs while creating the files.
 */
    public static String[] createTextFiles(int n, int seed, int bound) throws IOException {
        String[] fileNames = new String[n];
        Random rand = new Random(seed);

        for (int i=0; i<n; i++) {
            fileNames[i] = "file_" + (i+1);
            int nLines = rand.nextInt(bound);
            createFile(fileNames[i], nLines);
        }
        return new String[2];
    }

     /** Creates a single text file with a given number of lines.
     *
     * @param fileName The name of the file to create.
     * @param nLines   The number of lines to include in the file.
     * @throws IOException If an error occurs while creating the file.
     */
    public static void createFile(String fileName, int nLines) throws IOException {
        File file = new File(fileName);
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(fileName);
        for(int i=0; i < nLines - 1; i++) {
            fileWriter.write("The rain in Spain falls mainly on the plain.\n");
        }
        fileWriter.write("The rain in Spain falls mainly on the plain.");
        fileWriter.close();
    }
    
      /**
     * Counts the number of lines in a set of files using a single thread.
     *
     * @param fileNames An array of file names.
     * @return The total number of lines in the files.
     */
    public static int getNumOfLines(String[] fileNames) {
        int totalLines = 0;
        for (String fileName : fileNames) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                while (br.readLine() != null) {
                    totalLines++;
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file: " + fileName);
                e.printStackTrace();
            }
        }
        return totalLines;
    }
    
        /**
     * Counts the number of lines in a set of files using multiple threads.
     *
     * @param fileNames An array of file names.
     * @return The total number of lines in the files.
     */
    public static int getNumOfLinesThreads(String[] fileNames) {
        int totalLines = 0;
        FileLineCounterThread[] threads = new FileLineCounterThread[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            threads[i] = new FileLineCounterThread(fileNames[i]);
            threads[i].start();
        }
        for (FileLineCounterThread thread : threads) {
            try {
                thread.join();
                totalLines += thread.getNumLines();
            } catch (InterruptedException e) {
                System.err.println("An error occurred while waiting for a thread to finish: " + thread.getName());
                e.printStackTrace();
            }
        }
        return totalLines;
    }
    
      /**
     * Counts the number of lines in a set of files using a thread pool.
     *
     * @param fileNames An array of file names.
     * @return The total number of lines in the files.
     */
    public static int getNumOfLinesThreadPool(String[] fileNames){
        int totalLines = 0;
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
        try {
            FileLineCounterCallable[] callables = new FileLineCounterCallable[fileNames.length];
            for (int i = 0; i < fileNames.length; i++) {
                callables[i] = new FileLineCounterCallable(fileNames[i]);
            }
            Future<Integer>[] futures = new Future[fileNames.length];
            for (int i = 0; i < fileNames.length; i++) {
                futures[i] = executor.submit(callables[i]);
            }
            for (Future<Integer> future : futures) {
                totalLines += future.get();
            }
        } catch (Exception e) {
            System.err.println("An error occurred while counting the lines in the files");
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        return totalLines;
    }
}
