package com.example.validationdemo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@Schema(
        title = "Person View DTO",
        description = "Person DTO used when returning the resource to the client."
)
public class PersonViewDto extends PersonEditDto {

    @Schema(
            description = "The ID of the person.",
            example = "1"
    )
    String id;
}
