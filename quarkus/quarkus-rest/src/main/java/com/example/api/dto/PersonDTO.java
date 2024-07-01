package com.example.api.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@Value
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PersonDTO extends EditPersonDTO {

    @EqualsAndHashCode.Exclude
    String id;
}
