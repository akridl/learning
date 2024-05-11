package com.example.person.exception;

public class NoSuchContactException extends RuntimeException {

    public NoSuchContactException(String message) {
        super(message);
    }
}
