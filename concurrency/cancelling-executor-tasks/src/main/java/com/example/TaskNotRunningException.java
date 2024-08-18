package com.example;

public class TaskNotRunningException extends RuntimeException {

    public TaskNotRunningException(String message) {
        super(message);
    }
}
