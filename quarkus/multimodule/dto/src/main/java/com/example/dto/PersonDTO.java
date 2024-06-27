package com.example.dto;

import com.example.common.validation.ValidationGroups;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class PersonDTO {

    @Null(groups = { ValidationGroups.WhenRequesting.class })
    String id;

    @NotBlank
    @Size(min = 2, max = 50)
    String givenName;

    @NotBlank
    @Size(min = 2, max = 30)
    String familyName;
}
