package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PersonService {

    @Transactional
    public Person savePerson(Person person) {
        person.persist();
        return person;
    }

    public List<Person> getAllPersons() {
        return Person.listAll();
    }
}
