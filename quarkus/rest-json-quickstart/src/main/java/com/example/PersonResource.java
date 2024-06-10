package com.example;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashSet;
import java.util.Set;

@Path("/persons")
public class PersonResource {

    private Set<Person> persons = new HashSet<>();

    @POST
    public Set<Person> addPerson(Person person) {
        persons.add(person);
        return persons;
    }

    @GET
    public Set<Person> getPersons() {
        return persons;
    }

    @DELETE
    public Set<Person> deletePerson(Person person) {
        persons.remove(person);
        return persons;
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN) // No need to specify, since it's default when returning String from the endpoint
    public String hello(@PathParam("name") String name) {
        return "Hello " + name;
    }
}
