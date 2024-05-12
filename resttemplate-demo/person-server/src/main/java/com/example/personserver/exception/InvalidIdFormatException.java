package com.example.personserver.exception;

public class InvalidIdFormatException extends RuntimeException {

    public InvalidIdFormatException(String message) {
        super(message);
    }
}
