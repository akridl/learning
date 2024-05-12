package com.example.resttemplateclient;

import com.example.generated.client.model.ApiError;
import com.example.generated.client.model.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@Slf4j
public class App implements CommandLineRunner {

    @Value("${server.url}")
    private String serverUrl;

    private static final String PERSON_BASE_URL = "/persons";

    private static final RestTemplate restTemplate = new RestTemplate();

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
        updatePerson(id, editPersonDto);

        PersonDto updatedPersonDto = getPersonById(id).getBody();
        log.info("updated person: {}", updatedPersonDto);

        deletePersonById(id);

        try {
            ResponseEntity<ApiError> getPersonResponse = restTemplate.getForEntity(
                    serverUrl + PERSON_BASE_URL + "/{id}",
                    ApiError.class,
                    id
            );
            assert (getPersonResponse.getStatusCode().equals(HttpStatus.NOT_FOUND));
            log.info("response: {}", getPersonResponse.getBody());
        } catch (RestClientException ex) {
            log.error("exception caught: {}", ex.getMessage());
        }
    }

    private ResponseEntity<PersonDto> createPerson(PersonDto createPersonDto) {
        return restTemplate.postForEntity(
                serverUrl + PERSON_BASE_URL,
                createPersonDto,
                PersonDto.class
        );
    }

    private ResponseEntity<PersonDto> getPersonById(String id) {
        return restTemplate.getForEntity(
                serverUrl + PERSON_BASE_URL + "/{id}",
                PersonDto.class,
                id
        );
    }

    private void updatePerson(String id, PersonDto editPersonDto) {
        restTemplate.put(
                serverUrl + PERSON_BASE_URL + "/{id}",
                editPersonDto,
                1
        );
    }

    private void deletePersonById(String id) {
        restTemplate.delete(
                serverUrl + PERSON_BASE_URL + "/{id}",
                id
        );
    }

    private static PersonDto getSamplePersonDto() {
        return new PersonDto()
                .givenName("John")
                .familyName("Doe")
                .email("john.doe@example.com")
                .birthday(LocalDate.of(2020, Month.FEBRUARY, 2));
    }
}
