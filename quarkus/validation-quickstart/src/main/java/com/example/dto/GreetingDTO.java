package com.example.dto;

import com.example.validation.ValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Value;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Value
@Builder(toBuilder = true)
public class GreetingDTO {

    @Null(groups = { ValidationGroup.WhenCreating.class })
    @Schema(example = "42")
    String id;

    @NotBlank
    @Schema(example = "Yoyoyooo")
    String greeting;
}
