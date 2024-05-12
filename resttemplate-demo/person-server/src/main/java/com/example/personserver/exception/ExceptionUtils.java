package com.example.personserver.exception;

public class ExceptionUtils {

    private ExceptionUtils() {
        // Intentionally made private
    }

    public static Exception getInitialException(Exception ex) {
        while (ex.getCause() != null) {
            ex = (Exception) ex.getCause();
        }
        return ex;
    }
}
