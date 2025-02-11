package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UserLogger {

    @ConfigProperty(name = "app-config.user-logger-name")
    String userLoggerName;

    private Logger logger;

    public UserLogger() {
        logger = LoggerFactory.getLogger(getLoggerName(userLoggerName, AlignmentSubOperation.SCM_CLONE));
    }

    public void log(String stringToLog) {
        logger.info(stringToLog);
    }

    public void changeAlignmentSubOperation(@Observes AlignmentSubOperation alignmentSubOperation) {
        logger = LoggerFactory.getLogger(getLoggerName(userLoggerName, alignmentSubOperation));
    }

    private static String getLoggerName(String loggerName, AlignmentSubOperation alignmentSubOperation) {
        return loggerName + "." + alignmentSubOperation.getOperation();
    }
}
