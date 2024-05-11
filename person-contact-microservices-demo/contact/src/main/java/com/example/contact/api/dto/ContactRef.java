package com.example.contact.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;

@Value
@NonFinal
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Contact DTO intended to be used as a reference from another DTOs.")
public class ContactRef extends ContactEditDto {

    @Schema(
            description = "ID of the contact",
            example = "1"
    )
    @NotNull
    String id;
}
