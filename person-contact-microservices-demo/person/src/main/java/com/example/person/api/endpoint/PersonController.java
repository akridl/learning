package com.example.person.api.endpoint;

import com.example.person.api.dto.PersonDto;
import com.example.person.api.dto.PersonRef;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(
        path = "/persons",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Person")
public interface PersonController {

    @PostMapping
    @Operation(
            summary = "Create new person",
            responses = {
               @ApiResponse(
                       description = "Person successfully created",
                       responseCode = "201",
                       content = @Content(schema = @Schema(implementation = PersonDto.class))
               )
            }
    )
    ResponseEntity<PersonDto> createPerson(@Valid @RequestBody PersonRef createPersonDto);

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Get person by ID",
            responses = {
                    @ApiResponse(
                            description = "Person successfully returned",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDto.class))
                    )
            }
    )
    ResponseEntity<PersonDto> getPersonById(@PathVariable String id);

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete person by ID",
            responses = {
                    @ApiResponse(
                            description = "Person successfully deleted",
                            responseCode = "204"
                    )
            }
    )
    ResponseEntity<Void> deletePersonById(@PathVariable String id);

    @PostMapping("/{id}/assign-contact/{contactId}")
    @Operation(
            summary = "Assign contact to the person",
            responses = {
                    @ApiResponse(
                            description = "Contact successfully assigned",
                            responseCode = "200"
                    )
            }
    )
    ResponseEntity<PersonDto> assignContact(@PathVariable String id, @PathVariable String contactId);

    @DeleteMapping("/{id}/assign-contact/{contactId}")
    @Operation(
            summary = "Unassign contact from the person",
            responses = {
                    @ApiResponse(
                            description = "Contact successfully unassigned",
                            responseCode = "200"
                    )
            }
    )
    ResponseEntity<PersonDto> unassignContact(@PathVariable String id, @PathVariable String contactId);
}
