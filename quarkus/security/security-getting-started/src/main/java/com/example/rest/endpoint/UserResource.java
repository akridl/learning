package com.example.rest.endpoint;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

@RolesAllowed({ "user" })
@Path("/users")
@Produces(MediaType.TEXT_PLAIN)
public class UserResource {

    @GET
    @Path("/me")
    public String getUserInfo(@Context SecurityIdentity securityIdentity) {
        return "Hello " + securityIdentity.getPrincipal().getName() + "!";
    }
}
