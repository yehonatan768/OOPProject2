# Part 1 of OOPProject2
## What are we going to see:
In this project we are going to create files with random number of lines, and count them using 3 ways:
* A method using a processes that read each file sequentially, reading each line one at a time and adding it to the total count.
* A method that creates a separate thread for each file and starts each thread. The threads run concurrently.
* A method that uses a fixed thread pool with a number of threads equal to the number of files.

## Explanation and comparison between the functions:
### getNumOfLines
This method processes each file sequentially, reading each line one at a time and adding it to the total count. The runtime of this method will depend on the number and size of the files, as well as the speed of the machine that it is running on.

### getNumOfLinesThreads
This method creates a separate thread for each file and starts each thread. The threads run concurrently, so the runtime of this method will depend on the number and size of the files, as well as the number of available cores on the machine that it is running on. The runtime of this method may be faster than getNumOfLines if there are multiple cores available, but it may also be slower due to the overhead of creating and managing multiple threads.

### getNumOfLinesThreadPool
This method uses a fixed thread pool with a number of threads equal to the number of files. It creates a FileLineCounterCallable for each file and submits it to the executor service. The callables are executed concurrently by the threads in the thread pool, so the runtime of this method will depend on the number and size of the files, as well as the number of threads in the thread pool. The runtime of this method may be faster than getNumOfLines or getNumOfLinesThreads if there are multiple cores available and the overhead of creating and managing threads is minimized by using a thread pool.


## UML Diagram for the Project

![UML](https://github.com/yehonatan768/OOPProject2/blob/85f8a961e4cd2544f9c589567495153166b080a7/Part%201/UML.png)
