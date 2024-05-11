package com.example.contact.validation;

import com.example.contact.api.dto.ContactRef;
import com.example.validation.ValidationGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ContactValidator {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    public static void validateContact(ContactRef contactRef, Class<? extends ValidationGroup> validationGroup) {
        Set<ConstraintViolation<ContactRef>> validationResult = validator.validate(contactRef, validationGroup);
        if (!validationResult.isEmpty()) {
            throw new IllegalArgumentException("Contact's ID has to be null when creating new entity.");
        }
    }
}
