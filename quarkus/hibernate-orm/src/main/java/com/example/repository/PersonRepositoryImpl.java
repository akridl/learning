package com.example.repository;

import com.example.model.Person;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

@Dependent
@Transactional
public class PersonRepositoryImpl implements PersonRepository {

    @Inject
    public Session persistenceSession;

    @Override
    public Person createPerson(String name) {
        Person person = new Person();
        person.setName(name);
        persistenceSession.persist(person);
        return person;
    }

    @Override
    public Person getPersonById(Long id) {
        return persistenceSession.find(Person.class, id);
    }
}
