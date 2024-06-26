package com.example.endpoint;

import com.example.dto.CreatePersonDTO;
import com.example.dto.PersonDTO;
import com.example.facade.PersonFacade;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/persons")
@Slf4j
public class PersonEndpoint {

    private final PersonFacade personFacade;

    @Inject
    public PersonEndpoint(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @POST
    public PersonDTO createPersonAutomaticValidation(/* @Valid */ CreatePersonDTO createPersonDto) {
        // Validated (very probably) by interceptor before the actual method invocation, which is triggered by proxy
        // Hence, we do NOT even get into this log (OFC in case it's uncommented)
        log.info("PersonEndpoint#createPersonAutomaticValidation()");
        return personFacade.createPerson(createPersonDto);
    }

    @POST
    @Path("/manual-validation")
    public PersonDTO createPersonManualValidation(PersonDTO createPersonDto) {
        log.info("PersonEndpoint#createPersonManualValidation()");
        return personFacade.createPersonWithManualValidation(createPersonDto);
    }
}
