import java.io.IOException;

/**
 * A class for comparing the performance of three functions that count the number of lines in a set of text files.
 * The first function uses a single thread, the second uses multiple threads, and the third uses a thread pool.
 *
 * @author  [Your Name]
 * @version [Insert Version Number]
 * @since   [Insert Date]
 */
public class EX2 {
    
    /**
 * Calls the 'getNumOfLines' function in EX2_1 and measures the time taken to execute the function.
 *
 * @param fileNames An array of file names.
 */
    public static void activateFunction1(String[] fileNames) {
        try {
            long startTime = System.nanoTime();
            EX2_1.getNumOfLines(fileNames);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Total time for first function is: " + totalTime / 1000000000.0);
        } catch (Exception e) {
            System.out.println("[Exception] An error occurred in function 'getNumOfLines'");
        }
    }
    
    /**
 * Calls the 'getNumOfLinesThreads' function in EX2_1 and measures the time taken to execute the function.
 *
 * @param fileNames An array of file names.
 */
    public static void activateFunction2(String[] fileNames) {
        try {
            long startTime = System.nanoTime();
            EX2_1.getNumOfLinesThreads(fileNames);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Total time for second function is: " + totalTime / 1000000000.0);
        } catch (Exception e) {
            System.out.println("[Exception] An error occurred in function 'getNumOfLinesThreads'");
        }
    }
    
    /**
 * Calls the 'getNumOfLinesThreadPool' function in EX2_1 and measures the time taken to execute the function.
 *
 * @param fileNames An array of file names.
 */
    public static void activateFunction3(String[] fileNames) {
        try {
            long startTime = System.nanoTime();
            EX2_1.getNumOfLinesThreadPool(fileNames);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Total time for third function is: " + totalTime /1000000000.0);
        } catch (Exception e) {
            System.out.println("[Exception] An error occurred in function 'getNumOfLinesThreadPool'");
        }
    }
    
    /**
 * The main method of the EX2 class. Creates a specified number of text files, calls the three functions that
 * count the number of lines in the files, and measures the time taken to execute each function. The main method
 * also deletes the created text files.
 *
 * @param args Command line arguments (not used).
 * @throws IOException If an error occurs while creating or deleting the text files.
 */
    public static void main(String[] args) throws IOException {
        int N = 1000, SEED = 22321, BOUND = 3000;
        String[] fileNames = EX2_1.createTextFiles(N,SEED,BOUND);

        activateFunction1(fileNames);
        activateFunction2(fileNames);
        activateFunction3(fileNames);

        try {
            EX2_1.deleteFiles(fileNames);
        } catch (Exception e) {
            System.out.println("[Exception] An error occurred in function 'deleteFiles'");
        }
    }
}
