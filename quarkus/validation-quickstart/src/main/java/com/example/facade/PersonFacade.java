package com.example.facade;

import com.example.dto.CreatePersonDTO;
import com.example.dto.PersonDTO;
import com.example.validation.ValidationGroup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
@Slf4j
public class PersonFacade {

    private final Validator validator;

    @Inject
    public PersonFacade(Validator validator) {
        this.validator = validator;
    }

    public PersonDTO createPerson(@Valid CreatePersonDTO createPersonDto) {
        log.info("PersonFacade#createPerson()");
        return PersonDTO.builder()
                .id("1")
                .givenName(createPersonDto.getGivenName())
                .familyName(createPersonDto.getFamilyName())
                .birthday(createPersonDto.getBirthday())
                .build();
    }

    public PersonDTO createPersonWithManualValidation(PersonDTO createPersonDto) {
        log.info("PersonFacade#createPersonWithManualValidation()");
        Set<ConstraintViolation<PersonDTO>> result = validator.validate(createPersonDto, ValidationGroup.WhenCreating.class);
        if (!result.isEmpty()) {
            throw new IllegalArgumentException(result.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(", ")));
        }

        // Yeah yeah, map the DTO to domain type, call service, map vice versa
        return createPersonDto.toBuilder()
                .id("42")
                .build();
    }
}
