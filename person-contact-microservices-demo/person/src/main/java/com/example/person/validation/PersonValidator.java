package com.example.person.validation;

import com.example.person.api.dto.PersonRef;
import com.example.validation.ValidationGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class PersonValidator {

    private final static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    private final static Validator validator = validatorFactory.getValidator();

    public static void validatePerson(PersonRef personRef, Class<? extends ValidationGroup> validationGroup) {
        Set<ConstraintViolation<PersonRef>> validationResult = validator.validate(personRef, validationGroup);
        if (!validationResult.isEmpty()) {
            throw new IllegalArgumentException("Person's ID has to be null when creating new entity.");
        }
    }
}
