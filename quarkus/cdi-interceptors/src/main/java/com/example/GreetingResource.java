package com.example;

import io.quarkus.runtime.Quarkus;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/hello")
@Slf4j
public class GreetingResource {

    @Inject
    // Bean bean;
    Instance<Bean> beans;

    private Bean bean;

    @PostConstruct
    public void init() {
        log.info("[GreetingResource#init] Greeting resource is being initialized");
        for (Bean bean : beans) {
            if ("I'm the best!".equals(bean.pick())) {
                this.bean = bean;
            }
        }
        if (bean == null) {
            log.warn("[GreetingResource#init] No sufficient bean was found, exiting...");
            Quarkus.blockingExit();
        }

        log.info("[GreetingResource#init] Everything's initialized!!");
    }

    @PreDestroy
    public void destroy() {
        log.info("[GreetingResource#destroy] Greeting resource will be destroyed very soon");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var x = bean.doSomething();
        return "Hello from Quarkus REST";
    }
}
