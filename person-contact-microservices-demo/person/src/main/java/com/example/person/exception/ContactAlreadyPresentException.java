package com.example.person.exception;

public class ContactAlreadyPresentException extends RuntimeException {

    public ContactAlreadyPresentException(String message) {
        super(message);
    }
}
