package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@ApplicationScoped
@Path("/hello")
@Slf4j
public class HelloResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        log.debug("MDC is: " + MDC.getCopyOfContextMap());

        return "Hello there!";
    }
}
