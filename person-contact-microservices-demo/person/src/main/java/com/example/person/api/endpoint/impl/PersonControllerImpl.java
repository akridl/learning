package com.example.person.api.endpoint.impl;

import com.example.person.api.dto.PersonDto;
import com.example.person.api.dto.PersonRef;
import com.example.person.api.endpoint.PersonController;
import com.example.person.facade.PersonFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonControllerImpl implements PersonController {

    private final PersonFacade personFacade;

    public PersonControllerImpl(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @Override
    public ResponseEntity<PersonDto> createPerson(PersonRef createPersonDto) {
        return new ResponseEntity<>(personFacade.createPerson(createPersonDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PersonDto> getPersonById(String id) {
        return ResponseEntity.ok(personFacade.getPersonById(id));
    }

    @Override
    public ResponseEntity<Void> deletePersonById(String id) {
        personFacade.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PersonDto> assignContact(String id, String contactId) {
        return ResponseEntity.ok(personFacade.assignContact(id, contactId));
    }

    @Override
    public ResponseEntity<PersonDto> unassignContact(String id, String contactId) {
        return ResponseEntity.ok(personFacade.unassignContact(id, contactId));
    }
}
