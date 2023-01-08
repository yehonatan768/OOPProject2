# Part 2 of OPProject2
## What are we going to see:
In this part of the project we created a Custom ThreadPool with Priorities for the tasks in the ThreadPool. Every Task in the ThreadPool is from Type Task.
The custom thread pool executes tasks by priority,
and for that we used a priority blocking queue data structure. We chose this type of structure because we wanted to sort the tasks by

their priority, In addition we used a blocking queue becuase it needs to block a thread if the queue is empty or full.

This can be useful when you want to synchronize the access to the queue between multiple thread
