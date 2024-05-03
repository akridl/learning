package com.example.validationdemo.api.dto;

import com.example.validationdemo.validation.ValidationUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@NonFinal
@SuperBuilder(toBuilder = true)
@Jacksonized
@Schema(
        title = "Person Edit DTO",
        description = "Person DTO used when creating / editing the resource."
)
public class PersonEditDto {

    @Schema(
            description = "The first name of the person.",
            example = "John"
    )
    @Pattern(regexp = ValidationUtils.NAME_PATTERN)
    String givenName;

    @Schema(
            description = "The family name of the person.",
            example = "Doe"
    )
    @Pattern(regexp = ValidationUtils.NAME_PATTERN)
    String familyName;

    @Schema(
            description = "The email of the person.",
            example = "john.doe@example.com"
    )
    @Email(regexp = ValidationUtils.EMAIL_PATTERN)
    String email;

    @Schema(
            description = "The birtday of the person.",
            example = "2000-01-01"
    )
    @Past
    LocalDate birthday;
}
