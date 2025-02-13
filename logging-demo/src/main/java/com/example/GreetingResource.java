package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.stream.Stream;

@Path("/hello")
@Slf4j
public class GreetingResource {

    @Inject
    @UserLogger
    Logger userLogger;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        doSomething().forEach(this::consumeLogLine);
        log.info("tralala");

        return "Hello from Quarkus REST";
    }

    private Stream<String> doSomething() {
        return Stream.of("One", "2", "three", "42");
    }

    private void consumeLogLine(String logLine) {
        userLogger.debug(logLine);
        // fileLogger.info(logLine);
    }
}
