package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/operations")
public class OperationResource {

    private final OperationExecutor executor;

    @Inject
    public OperationResource(OperationExecutor executor) {
        this.executor = executor;
    }

    @POST
    @Path("/start")
    public Integer start() {
        return executor.start();
    }

    @POST
    @Path("/abort/{taskID}")
    public boolean abort(@PathParam("taskID") Integer taskID) {
        return executor.abort(taskID);
    }
}
