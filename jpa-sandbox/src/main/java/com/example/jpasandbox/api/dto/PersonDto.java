package com.example.jpasandbox.api.dto;

import com.example.jpasandbox.validation.WhenCreating;
import com.example.jpasandbox.validation.WhenUpdating;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class PersonDto {

    @Null(groups = { WhenCreating.class, WhenUpdating.class })
    String id;

    @NotNull
    String givenName;

    @NotNull
    String familyName;

    @PositiveOrZero
    int age;
}
