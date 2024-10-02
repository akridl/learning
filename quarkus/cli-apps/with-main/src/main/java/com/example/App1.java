package com.example;

import io.quarkus.runtime.QuarkusApplication;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App1 implements QuarkusApplication {

    private final GreetingService service;

    @Inject
    public App1(GreetingService service) {
        this.service = service;
    }

    @Override
    public int run(String... args) throws Exception {
        log.info("App code starts here!");

        try {
            String name = args[0];
            System.out.println(service.greet(args[0]));
            return 0;
        } catch (Exception e) {
            System.err.println("No name was given, exiting...");
            return 1;
        }

    }
}
