package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.slf4j.Logger;

@ApplicationScoped
public class LoggerFactory {

    private final Logger userLogger = org.slf4j.LoggerFactory.getLogger("org.jboss.pnc._userlog_.alignment-log");

    private final Logger fileLogger = org.slf4j.LoggerFactory.getLogger("file-log");

    @Produces
    @LiveLogger
    @ApplicationScoped
    Logger userLogger() {
        // System.out.println("user logger's name: " + userLogger.getName());
        return userLogger;
    }

    @Produces
    @FinalLogger
    @ApplicationScoped
    Logger fileLogger() {
        // System.out.println("file logger's name: " + fileLogger.getName());
        return fileLogger;
    }
}
