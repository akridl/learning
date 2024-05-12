package com.example.personserver.api.endpoint;

import com.example.personserver.api.dto.ApiError;
import com.example.personserver.api.dto.PersonDto;
import com.example.personserver.api.dto.PersonFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
        path = "/persons",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Person")
public interface PersonController {

    @Operation(
            summary = "Create new person",
            responses = {
                    @ApiResponse(
                            description = "Person successfully created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    @PostMapping
    ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonDto createPersonDto);

    @Operation(
            summary = "Get person by ID",
            responses = {
                    @ApiResponse(
                            description = "Person successfully returned",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Resource not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<PersonDto> getPersonById(@PathVariable String id);

    @Operation(
            summary = "Get persons paged",
            responses = {
                    @ApiResponse(
                            description = "Page of persons successfully returned",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SwaggerConstants.PersonPage.class))
                    )
            }
    )
    @GetMapping
    ResponseEntity<Page<PersonDto>> getPersons(@BeanParam PersonFilter personFilter);

    @Operation(
            summary = "Update person",
            responses = {
                    @ApiResponse(
                            description = "Person successfully returned",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Resource not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<PersonDto> updatePerson(@PathVariable String id, @Valid @RequestBody PersonDto editPersonDto);

    @Operation(
            summary = "Delete the person by ID",
            responses = {
                    @ApiResponse(
                            description = "Person successfully deleted",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    ),
                    @ApiResponse(
                            description = "Resource not found",
                            responseCode = "404",
                            content = @Content(schema = @Schema(implementation = ApiError.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePersonById(@PathVariable String id);
}
