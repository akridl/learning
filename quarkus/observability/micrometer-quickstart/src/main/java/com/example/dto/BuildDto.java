package com.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Jacksonized
@Value
public class BuildDto extends CreateBuildDto {

    String id;
}
