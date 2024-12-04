package org.example;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MyHandler extends Handler {

    private final Handler delegate;

    public MyHandler() {
        delegate = new ConsoleHandler();
        delegate.setFormatter(new MyFormatter());
        delegate.setFilter(new MyFilter());
        delegate.setLevel(Level.INFO);
    }

    @Override
    public void publish(LogRecord logRecord) {
        delegate.publish(logRecord);
    }

    @Override
    public void flush() {
        delegate.flush();
    }

    @Override
    public void close() throws SecurityException {
        delegate.close();
    }
}
