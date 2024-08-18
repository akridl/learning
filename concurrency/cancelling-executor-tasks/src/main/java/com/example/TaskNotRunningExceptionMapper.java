package com.example;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TaskNotRunningExceptionMapper implements ExceptionMapper<TaskNotRunningException> {

    @Override
    public Response toResponse(TaskNotRunningException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse(exception))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
