package com.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/persons")
public class PersonResource {

    @Inject
    PersonService personService;

    @POST
    public Person createPerson(Person person) {
        return personService.savePerson(person);
    }

    @GET
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }
}
