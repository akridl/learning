package com.example;

import io.quarkus.runtime.Shutdown;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class AppLifecycleBean {

    @Inject
    LazilyCreatedBean lazyBean;

    void onStartup(@Observes StartupEvent startupEvent) {
        log.info("[AppLifecycleBean#onStartup] app is starting!");
    }

    @Startup
    void onStartup2() {
        log.info("[AppLifecycleBean#onStartup2] app is starting!");
    }

    void onShutdown(@Observes ShutdownEvent shutdownEvent) {
        log.info("[AppLifecycleBean#onShutdown] app is shutting down!");
    }

    @Shutdown
    void onShutdown2() {
        log.info("[AppLifecycleBean#onShutdown2] app is shutting down!");
    }
}
