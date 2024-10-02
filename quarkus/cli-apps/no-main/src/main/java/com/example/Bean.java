package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class Bean {

    public void doSomething() {
        log.info("Doing some hard work over here...");
    }
}
