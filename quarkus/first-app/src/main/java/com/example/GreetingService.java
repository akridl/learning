package com.example;

import jakarta.enterprise.context.RequestScoped;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
public class GreetingService {

    public String greeting(String name) {
        log.info("greetingService#greeting() called at: {}", this);
        return "Hello " + name;
    }
}
