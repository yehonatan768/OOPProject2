# Part 2 of OPProject2
## What are we going to see:
In this project we created a Custom ThreadPool with Priorities for the tasks in the ThreadPool. Every Task in the ThreadPool is from Type Task.
The custom thread pool executes tasks by priority using a Priority Blocking Queue Data Structure.


### Explantion of the Project:
In this Project we used Factory-Design-Pattern, which belongs to the group of Creational Patterns.
We Implemented this Design-Pattern in the Task class to create more simpler Object to represent the Task, without revealing the creational logic behind it.


### Why do we use Priority Blocking Queue:
* Priority Blocking Queue helps us save the Tasks by thier priorities.
* Priority Blocking Queue helps us block a thread if the queue is empty or full.
* Priority Blocking Queue helps us synchronize the access to the queue between multiple threads.


## Uses and Advantages:
The implementation of a custom thread pool allows for greater control over task execution and can improve performance. By prioritizing tasks and utilizing a sorting queue, higher priority tasks can be completed first, increasing efficiency. Additionally, the use of a generic task class and the ability to create tasks using lambda expressions adds to the flexibility and maintainability of the system. The added benefit of being able to access the maximum priority of tasks in a time and space efficient manner further enhances the utility of this approach.


## UML Diagram for the Project

![UML](https://github.com/yehonatan768/OOPProject2/blob/f2f35d3c5e4dcb110eb726ea0e1851718028931e/Part%202/UML.png)
