package com.example;

import com.example.dto.AdjustResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Path("/operation")
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class OperationResource {

    @POST
    @Path("/adjust/complete")
    public Response completeAdjust(AdjustResponse adjustResponse) {
        log.info("Operation is being completed..");
        log.info("Got the response: {}", adjustResponse);

        return Response.status(201).entity("Adjustment completion is being started..").build();
    }
}
