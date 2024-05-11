package com.example.person.facade;

import com.example.person.api.dto.PersonDto;
import com.example.person.api.dto.PersonRef;
import com.example.person.mapper.PersonMapper;
import com.example.person.service.PersonService;
import com.example.person.validation.WhenCreating;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class PersonFacade {

    private final PersonService personService;

    private final PersonMapper personMapper;

    private final static ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
    private final static Validator validator = validationFactory.getValidator();

    @Autowired
    public PersonFacade(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    public PersonDto createPerson(PersonRef personDto) {
        validatePerson(personDto);
        return personMapper.toDto(personService.createPerson(personMapper.toEntity(personDto)));
    }

    @Transactional(readOnly = true)
    public PersonDto getPersonById(String id) {
        return personMapper.toDto(personService.getPersonById(personMapper.getIdMapper().toEntityId(id)));
    }

    public void deletePersonById(String id) {
        personService.deletePersonById(personMapper.getIdMapper().toEntityId(id));
    }

    private static void validatePerson(PersonRef personDto) {
        Set<ConstraintViolation<PersonRef>> validationResult = validator.validate(personDto, WhenCreating.class);
        if (!validationResult.isEmpty()) {
            throw new IllegalArgumentException("Person's ID has to be null when creating new entity.");
        }
    }
}
