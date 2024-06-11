package com.example;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.HashSet;
import java.util.Set;

@Path("/persons")
public class PersonResource {

    private Set<Person> persons = new HashSet<>();

    @POST
    public Set<Person> create(Person person) {
        persons.add(person);
        return persons;
    }

    @GET
    public Set<Person> list() {
        return persons;
    }

    @DELETE
    public Set<Person> delete(Person person) {
        persons.remove(person);
        return persons;
    }
}
