package com.example.person.service;

import com.example.exception.ResourceNotFoundException;
import com.example.person.data.model.Person;
import com.example.person.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> createNotFoundException(id));
    }

    public void deletePersonById(Long id) {
        if (personRepository.findById(id).isEmpty()) {
            throw createNotFoundException(id);
        }
        personRepository.deleteById(id);
    }

    private static ResourceNotFoundException createNotFoundException(Long id) {
        return new ResourceNotFoundException("Person with id=" + id + " not found.");
    }
}
