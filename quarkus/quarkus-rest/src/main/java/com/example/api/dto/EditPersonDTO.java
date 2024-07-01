package com.example.api.dto;

import jakarta.validation.constraints.Past;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@NonFinal
@SuperBuilder(toBuilder = true)
@Jacksonized
public class EditPersonDTO {

    String givenName;

    String familyName;

    @Past
    LocalDate birthday;
}
