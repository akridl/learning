package com.example.repository;

import com.example.model.Greeting;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

@Dependent
@Transactional
public class GreetingRepositoryImpl implements GreetingRepository {

    @Inject
    public Session persistenceSession;

    @Override
    public Greeting createGreeting(String greet) {
        Greeting greeting = new Greeting();
        greeting.setGreeting(greet);
        persistenceSession.persist(greeting);
        return greeting;
    }

    @Override
    public Greeting getGreeting(Long id) {
        return persistenceSession.find(Greeting.class, id);
    }
}
