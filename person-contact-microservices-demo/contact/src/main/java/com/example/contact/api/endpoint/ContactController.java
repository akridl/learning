package com.example.contact.api.endpoint;

import com.example.contact.api.dto.ContactDto;
import com.example.contact.api.dto.ContactRef;
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
        path = "/contacts",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@Tag(name = "Contact")
public interface ContactController {

    @PostMapping
    @Operation(
            summary = "Create new contact",
            responses = {
                    @ApiResponse(
                            description = "Contact successfully created",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ContactDto.class))
                    )
            }
    )
    ResponseEntity<ContactDto> createContact(@Valid @RequestBody ContactRef createContactDto);

    @GetMapping(path = "/{id}")
    @Operation(
            summary = "Get contact by ID",
            responses = {
                    @ApiResponse(
                            description = "Contact successfully returned",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ContactDto.class))
                    )
            }
    )
    ResponseEntity<ContactDto> getContactById(@PathVariable String id);

    @DeleteMapping(path = "/{id}")
    @Operation(
            summary = "Delete contact by ID",
            responses = {
                    @ApiResponse(
                            description = "Contact successfully deleted",
                            responseCode = "204"
                    )
            }
    )
    ResponseEntity<Void> deleteContactById(@PathVariable String id);
}
