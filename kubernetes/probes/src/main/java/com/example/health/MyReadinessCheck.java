package com.example.health;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
@Readiness
@Slf4j
public class MyReadinessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        log.info("Returning UP on Readiness check");
        return HealthCheckResponse.up("Readiness");
    }
}
