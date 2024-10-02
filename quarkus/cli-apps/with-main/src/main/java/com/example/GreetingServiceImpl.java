package com.example;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String greet(String name) {
        return String.format("Hello %s!", name);
    }
}
