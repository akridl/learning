package com.example.contact.exception;

import com.example.exception.ApiError;
import com.example.exception.InvalidIdException;
import com.example.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.UrlPathHelper;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandling {

    private static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<ApiError> handleNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = createApiError(HttpStatus.NOT_FOUND, ex, request);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({ InvalidIdException.class })
    public ResponseEntity<ApiError> handleInvalidException(InvalidIdException ex, HttpServletRequest request) {
        ApiError apiError = createApiError(HttpStatus.BAD_REQUEST, ex, request);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<ApiError> handleNotFoundException(IllegalArgumentException ex, HttpServletRequest request) {
        ApiError apiError = createApiError(HttpStatus.BAD_REQUEST, ex, request);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleAll(Exception ex, HttpServletRequest request) {
        ApiError apiError = createApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private static ApiError createApiError(HttpStatus status, Exception ex, HttpServletRequest request) {
        return ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(ex.getMessage())
                .path(URL_PATH_HELPER.getRequestUri(request))
                .build();
    }
}
