package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.flywaydb.core.Flyway;

@Path("/flyway")
public class FlywayResource {

    @Inject
    Flyway flyway;

    @GET
    public String getMigrationVersion() {
        return flyway.info().current().getVersion().toString();
    }
}
