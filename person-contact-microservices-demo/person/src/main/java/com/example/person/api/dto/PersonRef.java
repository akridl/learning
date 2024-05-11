package com.example.person.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Value
@NonFinal
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Jacksonized
@Validated
@Schema(description = "Person DTO intended to be used as a reference from another DTOs.")
public class PersonRef extends GenericEntityDto {

    @NotBlank
    @Schema(
            description = "Person's given name",
            example = "John"
    )
    String givenName;

    @NotBlank
    @Schema(
            description = "Person's family name",
            example = "Doe"
    )
    String familyName;

    @EqualsAndHashCode.Include
    @NotBlank
    @Email
    @Schema(
            description = "Person's email",
            example = "john.doe@example.com"
    )
    String  email;

    @NotNull
    @Past
    @Schema(
            description = "Person's birthday",
            example = "2002-02-02"
    )
    LocalDate birthday;
}
