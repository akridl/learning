package com.example.validationdemo.exception;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Value
@Builder
public class ApiError {

    LocalDateTime timestamp;
    HttpStatus status;
    String message;
    String path;
}
