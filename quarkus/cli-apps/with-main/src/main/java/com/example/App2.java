package com.example;

import io.quarkus.runtime.QuarkusApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App2 implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        log.info("App code 2 starts here!");
        System.out.println("***** Hi " + args[0] + "*****");

        return 0;
    }
}
