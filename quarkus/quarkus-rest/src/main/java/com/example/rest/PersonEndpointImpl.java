package com.example.rest;

import com.example.api.dto.EditPersonDTO;
import com.example.api.dto.PersonDTO;
import com.example.api.endpoint.PersonEndpoint;
import com.example.api.endpoint.request.CreatePersonParams;
import com.example.config.MyConfig;
import com.example.facade.PersonFacade;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalDate;

@ApplicationScoped
@Slf4j
public class PersonEndpointImpl implements PersonEndpoint {

    private final MyConfig config;

    private final PersonFacade personFacade;

    @Inject
    public PersonEndpointImpl(PersonFacade personFacade, MyConfig config) {
        this.personFacade = personFacade;
        this.config = config;
    }

    @Override
    public PersonDTO createPerson(EditPersonDTO createPersonDTO) {
        return personFacade.createPerson(createPersonDTO);
    }

    @Override
    public PersonDTO createPersonStrange(CreatePersonParams params) {
        String givenName = params.getGivenName();
        String familyName = params.getFamilyName() == null ? config.personConfig().defaultName() : params.getFamilyName();
        LocalDate birthday = params.getBirthday();

        log.info("key1: {}", params.getKey1());
        log.info("key2: {}", params.getKey2());

        return personFacade.createPerson(EditPersonDTO.builder()
                .givenName(givenName)
                .familyName(familyName)
                .birthday(birthday)
                .build());
    }

    @Override
    public RestResponse<String> pingPong(String text) {
        String result = "You pinged me with: \"" + text + "\"";
        return RestResponse.ResponseBuilder.ok(result, MediaType.TEXT_PLAIN_TYPE)
                .header("X-Ping", text)
                .header("X-Pong", result)
                .cookie(new NewCookie("Ping", text))
                .cookie(new NewCookie("Pong", result))
                .build();
    }
}
