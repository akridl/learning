package com.example.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Value
@Builder
@Schema(
        description = "Common API error DTO"
)
public class ApiError {

    @Schema(
            description = "Exact time of the error",
            example = "2024-05-10T12:13:14.567Z"
    )
    LocalDateTime timestamp;

    @Schema(
            description = "HTTP status code",
            example = "400"
    )
    HttpStatus status;

    @Schema(
            description = "Description of the error",
            example = "Validation error"
    )
    String message;

    @Schema(
            description = "Path of the request, which caused the error",
            example = "/api/v1/persons/-42"
    )
    String path;
}
