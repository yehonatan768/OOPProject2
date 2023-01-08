import java.util.concurrent.*;

public class CustomExecutor {
    final long MAXIDLETIME = 300; // section 9, 300 millisecond (0.3 seconds)
    private final PriorityBlockingQueue<Runnable> threadPool; // section 7
    private final ThreadPoolExecutor executor;
    private int maxPriority;

    public CustomExecutor() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int minProcessors =  availableProcessors / 2; // section 5
        int maxProcessors = availableProcessors - 1; // section 6
        threadPool = new PriorityBlockingQueue<>();
        executor = new ThreadPoolExecutor(minProcessors, maxProcessors, MAXIDLETIME, TimeUnit.MILLISECONDS, threadPool); // section 8
        maxPriority = Integer.MIN_VALUE;
    }

    // section 1
    public <T> Future<T> submit(Task<T> task) {
        maxPriority = Math.max(maxPriority, task.priority);
        return executor.submit(task);
    }

    // section 2
    public <T> Future<T> submit(Callable<T> callable, TaskType type) {
        Task<T> task = new Task<>(callable, type);
        maxPriority = Math.max(maxPriority, task.priority);
        return this.submit(task); // section 4
    }

    // section 3
    public <T> Future<T> submit(Callable<T> callable) {
        Task<T> task = new Task<T>(Task.createTask(callable));
        maxPriority = Math.max(maxPriority, task.priority);
        return this.submit(task); // section 4
    }

    public int getMaxPriority() {
        return this.maxPriority;
    }

    // section 10
    public int getCurrentMax() {
        return maxPriority;
    }

    // section 11
    public void gracefullyTerminate() {
        executor.shutdown();
    }
}