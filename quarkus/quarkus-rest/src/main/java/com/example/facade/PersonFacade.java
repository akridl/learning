package com.example.facade;

import com.example.api.dto.EditPersonDTO;
import com.example.api.dto.PersonDTO;
import com.example.mapper.PersonMapper;
import com.example.model.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PersonFacade {

    private final PersonMapper personMapper;

    @Inject
    public PersonFacade(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public PersonDTO createPerson(EditPersonDTO createPersonDTO) {
        Person person = personMapper.toEntity(createPersonDTO);
        person.persist();
        return personMapper.toDTO(person);
    }
}
