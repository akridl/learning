package com.example.jpasandbox.facade;

import com.example.jpasandbox.api.dto.PersonDto;
import com.example.jpasandbox.mapper.PersonMapper;
import com.example.jpasandbox.service.PersonService;
import com.example.jpasandbox.validation.WhenCreating;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonFacade {

    private final PersonService personService;

    private final PersonMapper personMapper;

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    @Autowired
    public PersonFacade(
            PersonService personService,
            PersonMapper personMapper
    ) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    public PersonDto createPerson(PersonDto personDto) {
        Set<ConstraintViolation<PersonDto>> constraintViolations = validator.validate(personDto, WhenCreating.class);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException("ID set when creating the person", constraintViolations);
        }
        return personMapper.fromEntity(personService.createPerson(personMapper.toEntity(personDto)));
    }

    public Page<PersonDto> getAllPersons(int pageIndex, int pageSize) {
        return personMapper.toDtoPage(personService.getAllPersons(pageIndex, pageSize));
    }
}
