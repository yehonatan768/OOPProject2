import java.util.concurrent.Callable;

public class Task<T> implements Callable<T>,Comparable<Task<T>>{
    private final Callable<T> operation;
    public int priority;

    public Task(Callable<T> operation,TaskType type) {
        this.operation = operation;
        this.priority = type.getPriorityValue();
    }
    public Task(Callable<T> operation) {
        this.operation = operation;
        this.priority = 4;
    }

    public static Task createTask(Callable operation,TaskType type) {
        return new Task(operation,type);
    }

    public static Task createTask(Callable operation) {
        return new Task(operation);
    }

    @Override
    public T call() throws Exception {
        return this.operation.call();
    }

    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(this.priority, other.priority);
    }

}
