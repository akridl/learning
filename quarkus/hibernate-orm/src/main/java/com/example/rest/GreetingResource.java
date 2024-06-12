package com.example.rest;

import com.example.model.Greeting;
import com.example.repository.GreetingRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/greetings")
public class GreetingResource {

    private final GreetingRepository greetingRepository;

    @Inject
    public GreetingResource(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @POST
    public Greeting createGreeting(String greeting) {
        return greetingRepository.createGreeting(greeting);
    }

    @GET
    @Path("/{id}")
    public Greeting getGreeting(@PathParam("id") String id) {
        return greetingRepository.getGreeting(Long.valueOf(id));
    }
}
