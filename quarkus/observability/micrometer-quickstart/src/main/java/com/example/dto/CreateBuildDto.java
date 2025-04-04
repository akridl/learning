package com.example.dto;

import com.example.enums.BuildType;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@SuperBuilder
@Jacksonized
@NonFinal
@Value
public class CreateBuildDto {

    BuildType buildType;
}
