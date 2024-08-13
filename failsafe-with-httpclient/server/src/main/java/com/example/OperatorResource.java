package com.example;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;

@Path("/operation")
@Slf4j
public class OperatorResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/complete")
    public String complete() {
        log.info("Completing the operation");
        return "Callback received";
    }
}
