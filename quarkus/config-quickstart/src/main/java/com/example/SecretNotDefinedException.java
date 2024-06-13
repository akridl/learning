package com.example;

public class SecretNotDefinedException extends RuntimeException {

    public SecretNotDefinedException(String message) {
        super(message);
    }
}
