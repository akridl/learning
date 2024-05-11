package com.example.contact.api.dto;

import com.example.contact.enums.ContactType;
import com.example.validation.WhenCreating;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@NonFinal
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Jacksonized
@Schema(description = "Contact DTO intended to be used as a reference from another DTOs.")
public class ContactRef {

    @Schema(
            description = "ID of the contact",
            example = "1"
    )
    @Null(groups = {WhenCreating.class})
    String id;

    @Schema(
            description = "Type of the contact",
            example = "EMAIL",
            implementation = ContactType.class
    )
    @NotNull
    ContactType type;

    @Schema(
            description = "Contact itself",
            example = "john.doe@example.com"
    )
    @EqualsAndHashCode.Include
    @NotBlank
    String contact;
}
