package com.example.personserver.validation;

import com.example.personserver.api.dto.PersonDto;
import com.example.personserver.validation.group.ValidationGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class PersonValidator {

    private final static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final static Validator validator = validatorFactory.getValidator();

    public static void validatePerson(PersonDto personDto, Class<? extends ValidationGroup> validationGroup) {
        Set<ConstraintViolation<PersonDto>> validationResult = validator.validate(personDto, validationGroup);
        if (!validationResult.isEmpty()) {
            throw new IllegalArgumentException(validationResult.iterator().next().getMessage());
        }
    }
}
