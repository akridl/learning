package com.example.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class GreetingResource {

    @GET
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
