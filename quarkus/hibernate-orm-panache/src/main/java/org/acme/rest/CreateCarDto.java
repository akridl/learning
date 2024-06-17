package org.acme.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize
public class CreateCarDto {

    String brand;

    String model;

    int yearOfConstruction;
}
