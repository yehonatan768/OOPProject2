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

public class EX2_1 {

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
