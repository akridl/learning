package com.example.mocks;

import com.example.GreetingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class GreetingServiceMock implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hola hola " + name;
    }
}
