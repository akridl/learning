package com.example.personserver.service;

import com.example.personserver.api.dto.PersonFilter;
import com.example.personserver.data.model.Person;
import com.example.personserver.data.repository.PersonRepository;
import com.example.personserver.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

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
    public Person findPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> createException(id));
    }

    @Transactional(readOnly = true)
    public Page<Person> findAll(PersonFilter personFilter) {
        // TODO
        return null;
    }

    public Person updatePerson(Long id, Person updatedPerson) {
        Person person = findPersonById(id);
        person.setGivenName(updatedPerson.getGivenName());
        person.setFamilyName(updatedPerson.getFamilyName());
        person.setBirthday(updatedPerson.getBirthday());
        return personRepository.save(person);
    }

    public void deletePersonById(Long id) {
        if (personRepository.findById(id).isEmpty()) {
            throw createException(id);
        }
        personRepository.deleteById(id);
    }

    private ResourceNotFoundException createException(Long id) {
        return new ResourceNotFoundException("Person with id=" + id + " not found.");
    }
}
