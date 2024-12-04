package org.example;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("my-logger");
        unregisterAllLoggerHandlers(logger);
        logger.addHandler(new MyHandler());

        // Won't pass the logging filter
        logger.info("Hello there!");                      // Does not contain neither 'Please' nor 'please'
        logger.log(Level.WARNING, "Yoyoooo");             // Same as above
        logger.log(Level.CONFIG, "Please please please"); // Handler does catch only log level INFO (and above), i.e. INFO, WARNING, and SEVERE

        // Will pass the logging filter
        logger.info("Please log: Hello there!");
        logger.severe("Hooooly, please, this is of the highest importance");
        logger.log(Level.WARNING, "Yoyoooo please");
    }

    private static void unregisterAllLoggerHandlers(Logger logger) {
        System.out.println("unregistering all the handlers");

        LogManager.getLogManager().reset();
        for (Handler handler : logger.getHandlers()) {
            System.out.println("unregistering " + handler);
            logger.removeHandler(handler);
        }
    }
}
