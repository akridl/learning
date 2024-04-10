package com.example.jpasandbox.api.endpoints;

import com.example.jpasandbox.api.SwaggerConstants;
import com.example.jpasandbox.api.dto.PersonDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@OpenAPIDefinition(
        info = @Info(
                title = "Person Service API",
                summary = "Sample Person Service API",
                version = "1.2.3"
        )
)
@RequestMapping("/persons")
@Tag(name = "Person")
public interface PersonController {

    @Operation(
            summary = "Create new person",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Person successfully created",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    )
            }
    )
    @PostMapping
    PersonDto createPerson(@RequestBody PersonDto person);

    @Operation(
            summary = "Get all persons (paged)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK, successfully returned.",
                            content = @Content(schema = @Schema(implementation = SwaggerConstants.PersonPage.class))
                    )
            })
    @GetMapping
    Page<PersonDto> getAllPersons(
            @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize
    );
}
