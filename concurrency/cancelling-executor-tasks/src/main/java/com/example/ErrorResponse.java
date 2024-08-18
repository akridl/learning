package com.example;

import lombok.Value;

@Value
public class ErrorResponse {

    String type;
    String message;

    public ErrorResponse(Throwable throwable) {
        this.type = throwable.getClass().getSimpleName();
        this.message = throwable.getMessage();
    }
}
