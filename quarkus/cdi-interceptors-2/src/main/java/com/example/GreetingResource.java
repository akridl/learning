package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    UserLogger userLogger;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        scmClone();
        align();
        pushResult();
        return "Hello from Quarkus REST";
    }

    @LoggedToUser(AlignmentSubOperation.SCM_CLONE)
    public void scmClone() {
        userLogger.log("cloning");
    }

    @LoggedToUser(AlignmentSubOperation.ALIGN)
    public void align() {
        userLogger.log("aligning");
    }

    @LoggedToUser(AlignmentSubOperation.PUSH_RESULT)
    public void pushResult() {
        userLogger.log("pushing a result");
    }
}
