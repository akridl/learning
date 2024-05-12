package com.example.personclientrestclient;

import com.example.generated.client.model.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {

    @Value("${person.server.base-url}")
    private String PERSON_SERVER_BASE_URL;

    private static final RestClient restClient = RestClient.create();

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<PersonDto> createPersonResponse = createPerson(getSamplePersonDto());

        PersonDto createdPersonDto = createPersonResponse.getBody();
        log.info("created person: {}", createdPersonDto);

        String id = createdPersonDto.getId();
        PersonDto editPersonDto = getSamplePersonDto().givenName("Jane");
        PersonDto updatedPersonDto = updatePerson(id, editPersonDto).getBody();
        log.info("updated person: {}", updatedPersonDto);

        ResponseEntity<PersonDto> getPerson1Response = getPersonById(id);
        assert (getPerson1Response.getStatusCode().is2xxSuccessful());
        log.info("found person: {}", getPerson1Response.getBody());

        ResponseEntity<Void> deleteResponse = deletePersonById(id);
        assert (deleteResponse.getStatusCode().is2xxSuccessful());

        try {
            ResponseEntity<PersonDto> getPerson2Response = getPersonById(id);
            assert (getPerson2Response.getStatusCode().is4xxClientError());
        } catch (Exception ex) {
            log.error("caught exception: {}", ex.getMessage());
        }
    }


    private ResponseEntity<PersonDto> createPerson(PersonDto createPersonDto) {
        return restClient.post()
                .uri(PERSON_SERVER_BASE_URL)
                .body(createPersonDto)
                .retrieve()
                .toEntity(PersonDto.class);
    }

    private ResponseEntity<PersonDto> getPersonById(String id) {
        return restClient.get()
                .uri(PERSON_SERVER_BASE_URL + "/{id}", Map.of("id", id))
                .retrieve()
                .toEntity(PersonDto.class);
    }

    private ResponseEntity<PersonDto> updatePerson(String id, PersonDto editPersonDto) {
        return restClient.put()
                .uri(PERSON_SERVER_BASE_URL + "/{id}", id)
                .body(editPersonDto)
                .retrieve()
                .toEntity(PersonDto.class);
    }

    private ResponseEntity<Void> deletePersonById(String id) {
        return restClient.delete()
                .uri(PERSON_SERVER_BASE_URL + "/{id}", id)
                .retrieve()
                .toEntity(Void.class);
    }

    private static PersonDto getSamplePersonDto() {
        return new PersonDto()
                .givenName("John")
                .familyName("Doe")
                .email("john.doe@example.com")
                .birthday(LocalDate.of(2020, Month.FEBRUARY, 2));
    }
}
