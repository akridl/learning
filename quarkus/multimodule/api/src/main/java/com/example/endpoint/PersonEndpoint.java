package com.example.endpoint;

import com.example.dto.PersonDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Person")
@Path("/persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PersonEndpoint {

    @Operation(summary = "Get all persons")
    @APIResponses({
            @APIResponse(
                    responseCode = OpenApiConstants.SUCCESS_CODE,
                    description = OpenApiConstants.SUCCESS_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = OpenApiConstants.PersonList.class))
            )
    })
    @GET
    List<PersonDTO> getPersons();

    @Operation(summary = "Create new person")
    @APIResponses({
            @APIResponse(
                    responseCode = OpenApiConstants.ENTITY_CREATED_CODE,
                    description = OpenApiConstants.ENTITY_CREATED_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = PersonDTO.class))
            )
    })
    @POST
    PersonDTO createPerson(PersonDTO createPersonDto);
}
