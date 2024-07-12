package com.example;

import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
@Startup
public class EagerlyCreatedBean {

    @PostConstruct
    public void init() {
        log.info("[EagerlyCreatedBean#init] app is starting!");
    }

    @PreDestroy
    public void destroy() {
        log.info("[EagerlyCreatedBean#destroy] app is shutting down!");
    }
}
