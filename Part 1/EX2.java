import java.io.IOException;


public class EX2 {

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