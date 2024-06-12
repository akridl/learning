package com.example.rest;

import com.example.repository.PersonRepository;
import com.example.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@ApplicationScoped  // Not needed, however at least it's explicit bean creation
@Path("/persons")
public class PersonResource {

    private final PersonRepository personRepository;

    @Inject
    public PersonResource(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @POST
    public Person createPerson(String name) {
        return personRepository.createPerson(name);
    }

    @GET
    @Path("/{id}")
    public Person getPerson(@PathParam("id") String id) {
        return personRepository.getPersonById(Long.valueOf(id));
    }
}
