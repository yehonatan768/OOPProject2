import java.util.HashMap;
import java.util.concurrent.*;

/**
 This class represents a custom thread pool executor that is capable of executing tasks with different priorities.
 The number of threads in the pool is set to half the number of processors available for the JVM. The maximum number of threads is set to the number of processors minus 1.
 The threads in the pool will remain alive for a maximum idle time of 300 milliseconds (0.3 seconds).
 A priority blocking queue is used to store the tasks and their priorities.
 @author Noy Dayan, Yehonatan Mekayten.
 @version 1.
 */
public class CustomExecutor {
    final long MAXIDLETIME = 300; // section 9, 300 millisecond (0.3 seconds)
    private final PriorityBlockingQueue<Runnable> threadPool; // section 7
    private final ThreadPoolExecutor executor;
    private int maxPriority;

    /**
     Constructor for the CustomExecutor class.
     Initializes the thread pool and the thread pool executor with the specified parameters.
     */
    public CustomExecutor() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int minProcessors =  availableProcessors / 2; // section 5
        int maxProcessors = availableProcessors - 1; // section 6
        threadPool = new PriorityBlockingQueue<>();
        executor = new ThreadPoolExecutor(minProcessors, maxProcessors, MAXIDLETIME, TimeUnit.MILLISECONDS, threadPool); // section 8
        maxPriority = Integer.MIN_VALUE;
    }

    /**
     * Submits a task with a specified priority to the thread pool executor.
     * Additionally, checks if the completed task had the highest priority, by calling the updateMaxPriority method and updates the maximum
     * priority of any task submitted to this executor accordingly.
     * @param task The task to be executed
     * @return A Future object representing the result of the task
     */
    // section 1
    public <T> Future<T> submit(Task<T> task) {
        maxPriority = Math.max(maxPriority, task.getPriority());

        Future<T> future = executor.submit(task);

        this.updateMaxPriority(future, task);

        return future;
    }
    /**
     * Updates the maximum priority of any task submitted to this executor, in case the completed task had the highest priority.
     *
     * @param future A Future object representing the result of the task
     * @param task the Task which is completed
     */
    public <T> void updateMaxPriority(Future<T> future, Task<T> task) {
        try {
            // Wait for task to complete
            T result = future.get();
            if (task.getPriority() == maxPriority) {
                int newMaxPriority = Integer.MIN_VALUE;
                for (Runnable r : threadPool) {
                    if (r instanceof Task) {
                        Task t = (Task) r;
                        newMaxPriority = Math.max(newMaxPriority, t.getPriority());
                    }
                }
                maxPriority = newMaxPriority;
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
    }

    /**
     Creates a task from a callable and a specified priority, and submits it to the thread pool executor.
     @param callable The callable representing the task to be executed
     @param taskType The priority of the task
     @return A Future object representing the result of the task
     */
    // section 2
    public <T> Future<T> submit(Callable<T> callable, TaskType taskType) {
        Task<T> task = new Task<>(callable, taskType);
        maxPriority = Math.max(maxPriority, task.getPriority());
        return this.submit(task); // section 4
    }

    /**
     Creates a task from a callable and submits it to the thread pool executor.
     The priority of the task is set to the default value.
     @param callable The callable representing the task to be executed.
     @return A Future object representing the result of the task.
     */
    // section 3
    public <T> Future<T> submit(Callable<T> callable) {
        Task<T> task = new Task<T>(Task.createTask(callable));
        maxPriority = Math.max(maxPriority, task.getPriority());
        return this.submit(task); // section 4
    }

    /**
     Returns the maximum priority of any task submitted to this executor.
     @return The maximum priority of any task submitted to this executor.
     */
    public int getMaxPriority() {
        return this.maxPriority;
    }

    /**
     Returns the current maximum priority of any task in the queue.
     @return The current maximum priority of any task in the queue.
     */
    // section 10
    public int getCurrentMax() {
        return maxPriority;
    }

    /**
     Shuts down the executor in a graceful manner.
     Allows currently executing tasks to complete, but no new tasks will be accepted.
     */
    // section 11
    public void gracefullyTerminate() {
        executor.shutdown();
    }
}
