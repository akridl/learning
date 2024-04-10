package com.example.jpasandbox.service;

import com.example.jpasandbox.data.model.Person;
import com.example.jpasandbox.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {


    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public Page<Person> getAllPersons(int pageIndex, int pageSize) {
        return personRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }
}
