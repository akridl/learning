package com.example.whateveriwant;

import com.example.generated.server.model.PersonDto;
import com.example.generated.server.rest.PersonApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController implements PersonApi {

    @Override
    public ResponseEntity<List<PersonDto>> getAllPersons() {
        PersonDto jane = new PersonDto();
        jane.setId(13L);
        jane.setGivenName("Jane");
        jane.setFamilyName("Doe");

        PersonDto john = new PersonDto();
        john.setId(42L);
        john.setGivenName("John");
        john.setFamilyName("Doe");

        return ResponseEntity.ok(List.of(jane, john));
    }
}
