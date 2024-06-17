package org.acme.rest;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.model.Person;

import java.time.LocalDate;
import java.time.Month;

@Path("/persons")
@Transactional
public class PersonResource {

    @POST
    @Path("/play")
    public String doStuff() {
        System.out.println("Starting");
        save();
        find();
        update();
        find();
        countAll();
        countAllJanes();
        return "Done";
    }

    @GET
    @Path("/{id}")
    // @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Person getPersonById(@PathParam("id") String id) {
        return Person.findById(id);
    }

    private void save() {
        Person person = new Person();
        person.setGivenName("Bonifacion");
        person.setFamilyName("Whatever");
        person.setBirthday(LocalDate.of(2022, Month.FEBRUARY, 2));
        person.persist();
    }

    private void find() {
        Person person = Person.findById(1L);
        System.out.println("Found person: " + person);
    }

    private void update() {
        System.out.println("updating...");
        Person person = getPersonById("1");
        person.setGivenName("Jane");
        person.persist();
    }

    private void countAll() {
        System.out.println("All persons in the DB count: " + Person.count());
    }

    private void countAllJanes() {
        System.out.println("All Janes in the DB count: " + Person.count("givenName", "Jane"));
    }
}
