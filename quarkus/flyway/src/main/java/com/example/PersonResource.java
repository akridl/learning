package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/persons")
public class PersonResource {

    @GET
    public List<Person> getAllPersons() {
        return Person.listAll();
    }
}
