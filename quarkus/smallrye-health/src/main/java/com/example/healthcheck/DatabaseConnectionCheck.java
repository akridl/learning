package com.example.healthcheck;

import com.example.config.MyConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ApplicationScoped
@Readiness
@Slf4j
public class DatabaseConnectionCheck implements HealthCheck {

    private final MyConfig.DatabaseConfig dbConfig;

    @Inject
    public DatabaseConnectionCheck(MyConfig config) {
        dbConfig = config.database();
    }

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.named("DB_READY")
                .withData("configLoaded", dbConfig != null)
                .withData("threshold", dbConfig == null ? 0 : dbConfig.validationThreshold());

        try {
            Connection connection = DriverManager.getConnection(dbConfig.url(), dbConfig.username(), dbConfig.password());
            boolean connectionReady = connection.isValid(dbConfig.validationThreshold());
            builder.status(connectionReady);
        } catch (SQLException e) {
            // This is super-coolish in case we can afford it
            builder.withData("error", e.getMessage());
            builder.status(false);
        }
        return builder.build();
    }
}
