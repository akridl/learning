package com.example.healthcheck;

import jakarta.inject.Singleton;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Startup;

import java.util.Optional;

@Singleton  // No need to use this, since it's the default when we @Startup / @Liveness / @Readiness
@Startup    // /q/${quarkus.smallrye-health.root-path}/started by default
public class MyStartupCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return new HealthCheckResponse("STARTED", HealthCheckResponse.Status.UP, Optional.empty());
        // return HealthCheckResponse.up("STARTED");
    }
}
