package com.example.api.endpoint;

import com.example.api.dto.EditPersonDTO;
import com.example.api.dto.PersonDTO;
import com.example.api.endpoint.openapi.OpenapiConstants;
import com.example.api.endpoint.request.CreatePersonParams;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

@Tag(name = "Person")
@Path("/persons")
public interface PersonEndpoint {

    @POST
    @ResponseStatus(201)
    @Operation(summary = "Create a person")
    @APIResponses({
            @APIResponse(
                    responseCode = OpenapiConstants.ENTITY_CREATED_CODE,
                    description = OpenapiConstants.ENTITY_CREATED_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = PersonDTO.class))
            )
    })
    PersonDTO createPerson(@Valid EditPersonDTO editPersonDTO);

    @GET
    @Path("/strange/givenName/{givenName}")
    @ResponseStatus(201)
    @Operation(summary = "Create a person, but straaaangely")
    @APIResponses({
            @APIResponse(
                    responseCode = OpenapiConstants.ENTITY_CREATED_CODE,
                    description = OpenapiConstants.ENTITY_CREATED_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = PersonDTO.class))
            )
    })
    PersonDTO createPersonStrange(@BeanParam CreatePersonParams params);

    @POST
    @Path("/ping/{text}")
    @ResponseStatus(201)
    RestResponse<String> pingPong(@PathParam("text") String text);
}
