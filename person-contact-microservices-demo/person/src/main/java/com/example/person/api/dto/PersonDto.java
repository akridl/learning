package com.example.person.api.dto;

import com.example.client_model.ContactRef;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Jacksonized
@Validated
@Schema(description = "Person DTO used when returning to the client.")
public class PersonDto extends PersonRef {

    @ArraySchema(schema = @Schema
            (
                    description = "Contacts of the person",
                    implementation = ContactRef.class
            )
    )
    List<ContactRef> contacts;
}
