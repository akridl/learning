package com.example.person.facade;

import com.example.person.api.dto.PersonDto;
import com.example.person.api.dto.PersonRef;
import com.example.person.data.model.Person;
import com.example.person.mapper.PersonMapper;
import com.example.person.service.PersonService;
import com.example.person.validation.PersonValidator;
import com.example.validation.WhenCreating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonFacade {

    private final PersonService personService;

    private final PersonMapper personMapper;

    @Autowired
    public PersonFacade(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    public PersonDto createPerson(PersonRef createPersonDto) {
        PersonValidator.validatePerson(createPersonDto, WhenCreating.class);
        return personMapper.toDto(personService.createPerson(personMapper.toEntity(createPersonDto)));
    }

    @Transactional(readOnly = true)
    public PersonDto getPersonById(String id) {
        return personMapper.toDto(personService.getPersonById(personMapper.getIdMapper().toEntityId(id)));
    }

    public void deletePersonById(String id) {
        personService.deletePersonById(personMapper.getIdMapper().toEntityId(id));
    }

    public PersonDto assignContact(String id, String contactId) {
        // TODO: Call contact microservice
        Person updatedPerson = personService.assignContact(personMapper.getIdMapper().toEntityId(id), personMapper.getIdMapper().toEntityId(contactId));
        return personMapper.toDto(updatedPerson);
    }

    public PersonDto unassignContact(String id, String contactId) {
        // TODO: Call contact microservice
        Person updatedPerson = personService.unassignContact(personMapper.getIdMapper().toEntityId(id), personMapper.getIdMapper().toEntityId(contactId));
        return personMapper.toDto(updatedPerson);
    }
}
