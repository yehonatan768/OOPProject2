import java.util.concurrent.Callable;

public class Task<T> implements Callable<T>,Comparable<Task<T>>{
    int DEFAULTPRIORITY = 4;
    private final Callable<T> operation;
    private int priority;

    // section 4 - part 1
    public Task(Callable<T> operation) {
        this.operation = operation;
        this.priority = DEFAULTPRIORITY;
    }

    // section 4 - part 2
    public Task(Callable<T> operation,TaskType type) {
        this.operation = operation;
        this.priority = type.getPriorityValue();
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static Task createTask(Callable operation) {
        return new Task(operation);
    }

    public static Task createTask(Callable operation,TaskType type) {
        return new Task(operation,type);
    }

    // section 2
    @Override
    public T call() throws Exception {
        return this.operation.call();
    }

    // section 5
    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(this.priority, other.getPriority());
    }

}
