package com.example.personserver.api.dto;

import com.example.personserver.validation.group.WhenCreating;
import com.example.personserver.validation.group.WhenUpdating;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@Builder
@Jacksonized
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(description = "Person DTO")
public class PersonDto {

    @Schema(
            description = "ID of the person",
            example = "1"
    )
    @Null(groups = {WhenCreating.class}, message = "ID of the person has to be null when creating new entity.")
    @Null(groups = {WhenUpdating.class}, message = "ID of the person has to be null when updating existing entity.")
    String id;

    @Schema(
            description = "Given name of the person",
            example = "John"
    )
    @NotBlank
    String givenName;

    @Schema(
            description = "Family name of the person",
            example = "Doe"
    )
    @NotBlank
    String familyName;

    @Schema(
            description = "Email of the person",
            example = "john.doe@example.com"
    )
    @Email
    String email;

    @Schema(
            description = "Birthday of the person",
            example = "2020-02-02"
    )
    @Past
    LocalDate birthday;
}
