package com.example.person.api.dto;

import com.example.person.validation.WhenCreating;
import jakarta.validation.constraints.Null;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;

@Value
@NonFinal
@SuperBuilder(toBuilder = true)
public abstract class GenericEntityDto {

    @Null(groups = { WhenCreating.class })
    protected String id;
}
