package com.example.endpoint;

import com.example.dto.GreetingDTO;
import com.example.validation.ValidationGroup;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Greeting")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/greetings")
public class GreetingEndpoint {

    @POST
    public GreetingDTO createGreeting(@Valid @ConvertGroup(to = ValidationGroup.WhenCreating.class) GreetingDTO createGreetingDto) {
        return createGreetingDto.toBuilder()
                .id("42")
                .build();
    }
}
