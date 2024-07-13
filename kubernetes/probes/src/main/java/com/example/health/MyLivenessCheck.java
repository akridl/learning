package com.example.health;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@ApplicationScoped
@Liveness
@Slf4j
public class MyLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        log.info("Returning UP on Liveness check");
        return HealthCheckResponse.up("Liveness");
    }
}
