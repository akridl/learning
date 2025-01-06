package com.example.rest.endpoint;

import com.example.model.Greeting;
import com.example.rest.dto.EditGreetingDto;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@PermitAll
@Path("/hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @POST
    @Transactional
    public Greeting create(EditGreetingDto createGreeting) {
        Greeting greeting = new Greeting();
        greeting.greeting = createGreeting.getGreeting();

        greeting.persist();
        return greeting;
    }

    @GET
    public List<Greeting> hello() {
        return Greeting.listAll();
    }

    @RolesAllowed({ "admin" })
    @GET
    @Path("/admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetAdmin() {
        return "Hello admin!!";
    }
}
