package com.example.internal;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InternalServiceImpl implements InternalService {

    @Override
    public String result() {
        return "Result from super-complicated implementation";
    }
}
