package com.example.contact.api.dto;

import com.example.contact.enums.ContactType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "Contact DTO used when creating / updating the resource.")
public class ContactEditDto {

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
