package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.slf4j.Logger;

@ApplicationScoped
public class LoggerFactory {

    private final Logger userLogger = org.slf4j.LoggerFactory.getLogger("org.jboss.pnc._userlog_.alignment-log");

    @Produces
    @UserLogger
    @ApplicationScoped
    Logger userLogger() {
        return userLogger;
    }
}
