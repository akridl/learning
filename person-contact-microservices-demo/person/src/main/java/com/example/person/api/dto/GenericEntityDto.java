package com.example.person.api.dto;

import com.example.validation.WhenCreating;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Null;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;

@Value
@NonFinal
@SuperBuilder(toBuilder = true)
public abstract class GenericEntityDto {

    @Null(groups = { WhenCreating.class })
    @Schema(description = "ID of the entity", example = "1")
    protected String id;
}
