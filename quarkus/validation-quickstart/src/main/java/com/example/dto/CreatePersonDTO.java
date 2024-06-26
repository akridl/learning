package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.NonFinal;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Value
@NonFinal
@Builder(toBuilder = true)
public class CreatePersonDTO {

    @NotBlank
    @Size(min = 2)
    @Schema(example = "John")
    String givenName;

    @NotBlank
    @Size(min = 2)
    @Schema(example = "Doe")
    String familyName;

    @NotNull
    @Past
    @Schema(example = "2024-02-02")
    LocalDate birthday;
}
