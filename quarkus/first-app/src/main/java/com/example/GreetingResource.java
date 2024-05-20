package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
@Slf4j
public class GreetingResource {

    private final GreetingService greetingService;

    @Inject
    public GreetingResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/{name}")
    public String greeting(@PathParam("name") String name) {
        log.info("greetingResource#greeting() called at: {}", this);
        return greetingService.greeting(name);
    }
}
