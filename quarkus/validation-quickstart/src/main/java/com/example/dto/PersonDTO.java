package com.example.dto;

import com.example.validation.ValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Value;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class PersonDTO {

    // When handling persons, we treat defined validation groups by old-school approach
    // Using injected Validator and doing the validation manually
    // However, quarkus offers also support for this, see @ConvertGroup used in GreetingEndpoint
    @Null(groups = { ValidationGroup.WhenCreating.class, ValidationGroup.WhenModifying.class })
    @Schema(example = "1")
    String id;

    @NotBlank
    @Schema(example = "John")
    String givenName;

    @NotBlank
    @Schema(example = "Doe")
    String familyName;

    @NotNull
    @Past
    @Schema(example = "2024-02-02")
    LocalDate birthday;
}
