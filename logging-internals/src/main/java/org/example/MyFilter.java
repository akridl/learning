package org.example;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.stream.Stream;

public class MyFilter implements Filter {

    @Override
    public boolean isLoggable(LogRecord logRecord) {
        return Stream.of("please", "Please").anyMatch(logRecord.getMessage()::contains);
    }
}
