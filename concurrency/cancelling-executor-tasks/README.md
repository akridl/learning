# Cancelling Executor tasks

Simple demo of how to work with cancellable tasks run on [ExecutorService](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/ExecutorService.html).

## Description
There are 2 endpoints: one for starting the task and another one for aborting the task by its ID.

When the task is scheduled and how long it computes (=sleeps) is [configurable](src/main/resources/application.yaml).

There are basically 3 alternatives (from the point of abort operartion):

1) We try to abort a task, which didn't start yet
   - In this case, ["exception is thrown"](src/main/java/com/example/TaskNotRunningExceptionMapper.java)

2) We try to abort a task, which is actually running
   - The only case, where aborting should succeed

3) We try to abort a task, which already finished
    - Analogical to 1)

## How to run
- Simply, `quarkus dev` is enough
