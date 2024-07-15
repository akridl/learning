package com.example;

import com.example.config.MyConfig;
import com.example.external.ExternalService;
import com.example.internal.InternalService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GreetingServiceImpl implements GreetingService {

    @Inject
    MyConfig config;

    @Inject
    InternalService internalService;

    @Inject
    @RestClient
    ExternalService externalService;

    public String greeting(String name) {
        return "Hello " + name + "!";
    }

    @Override
    public String internalResult() {
        return internalService.result();
    }

    @Override
    public String externalResult() {
        return externalService.result();
    }

    @Override
    public String getValue1() {
        return config.key1();
    }

    @Override
    public String getValue2() {
        return config.key2();
    }
}
