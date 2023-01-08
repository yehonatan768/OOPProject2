import java.util.concurrent.Callable;

/**
The Task class represents a unit of work that can be executed concurrently by a CustomExecutor.
It implements the Callable interface and the Comparable interface to allow the task to be executed by a thread
and compared to other tasks based on their priority.
@param <T> The type of the result returned by this task's call method
@author Noy Dayan, Yehonatan Mekayten.
@version 1.
*/
public class Task<T> implements Callable<T>,Comparable<Task<T>>{
    /** The default priority for a task if none is specified */
    int DEFAULTPRIORITY = 4;
    /** The operation to be performed by this task */
    private final Callable<T> operation;
    /** The priority of this task */
    private int priority;
    
    /**
    Constructs a new Task with the given operation and the default priority.
    @param operation The operation to be performed by this task
    */
    // section 4 - part 1
    public Task(Callable<T> operation) {
        this.operation = operation;
        this.priority = DEFAULTPRIORITY;
    }
    
    /**
    Constructs a new Task with the given operation and the specified priority.
    @param operation The operation to be performed by this task
    @param type The priority of this task, represented by a TaskType enum value
    */
    // section 4 - part 2
    public Task(Callable<T> operation,TaskType type) {
        this.operation = operation;
        this.priority = type.getPriorityValue();
    }
    
    /**
    Returns the priority of this task.
    @return The priority of this task
    */
    public int getPriority() {
        return this.priority;
    }
    
    /**
    Sets the priority of this task to the given value.
    @param priority The new priority for this task
    */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    /**
    A static factory method that creates a new {@code Task} instance with the specified {@code Callable} operation and default priority.
    @param operation the {@code Callable} operation to be performed by the created task
    @return a new {@code Task} instance with the specified {@code Callable} operation and default priority
    */
    public static Task createTask(Callable operation) {
        return new Task(operation);
    }
    
    /**
    A static factory method that creates a new {@code Task} instance with the specified {@code Callable} operation and {@code TaskType} priority.
    @param operation the {@code Callable} operation to be performed by the created task
    @param taskType the {@code TaskType} representing the priority of the created task
    @return a new {@code Task} instance with the specified {@code Callable} operation and {@code TaskType} priority
    */
    public static Task createTask(Callable operation,TaskType taskType) {
        return new Task(operation,taskType);
    }
    
    /**
    Executes the {@code Callable} operation associated with this task.
    @return the result of the operation
    @throws Exception if the operation throws an exception
    */
    // section 2
    @Override
    public T call() throws Exception {
        return this.operation.call();
    }
    
    /**
    Compares this task to another task based on their priorities.
    @param other the other task to compare to
    @return a negative integer, zero, or a positive integer as the priority of this task is less than, equal to, or greater than the priority of the other task
    */
    // section 5
    @Override
    public int compareTo(Task<T> other) {
        return Integer.compare(this.priority, other.getPriority());
    }
}
