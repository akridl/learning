package com.example.personserver.api.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.QueryParam;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Value
@Builder
@Jacksonized
public class PersonFilter {

    @QueryParam("pageIndex")
    @Parameter(
            description = "Page index",
            example = "0"
    )
    Integer pageIndex;

    @QueryParam("pageSize")
    @Parameter(
            description = "Page size",
            example = "5"
    )
    Integer pageSize;

    @QueryParam("givenName")
    @Parameter(
            description = "Given name of the person",
            example = "John"
    )
    String givenName;

    @QueryParam("familyName")
    @Parameter(
            description = "Family name of the person",
            example = "Doe"
    )
    String familyName;

    @QueryParam("email")
    @Parameter(
            description = "Regex for email of the person",
            example = ".+@example\\.com"
    )
    String emailRegex;

    @QueryParam("bornSince")
    @Parameter(
            description = "Lower bound for birthday search",
            example = "2020-02-02"
    )
    LocalDate bornSince;

    @QueryParam("bornUntil")
    @Parameter(
            description = "Upper bound for birthday search",
            example = "2020-02-20"
    )
    LocalDate bornUntil;
}
