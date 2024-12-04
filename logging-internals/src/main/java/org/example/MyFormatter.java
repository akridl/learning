package org.example;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord logRecord) {
        return String.format("[%s] (%s) %s\n", logRecord.getLevel(), logRecord.getLoggerName(), logRecord.getMessage());
    }
}
