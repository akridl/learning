package com.example.personserver.api.endpoint.impl;

import com.example.personserver.api.dto.PersonDto;
import com.example.personserver.api.dto.PersonFilter;
import com.example.personserver.api.endpoint.PersonController;
import com.example.personserver.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonControllerImpl implements PersonController {

    private final PersonFacade personFacade;

    @Autowired
    public PersonControllerImpl(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @Override
    public ResponseEntity<PersonDto> createPerson(PersonDto createPersonDto) {
        return new ResponseEntity<>(personFacade.createPerson(createPersonDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PersonDto> getPersonById(String id) {
        return ResponseEntity.ok(personFacade.findPersonById(id));
    }

    @Override
    public ResponseEntity<Page<PersonDto>> getPersons(PersonFilter personFilter) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PersonDto> updatePerson(String id, PersonDto editPersonDto) {
        return ResponseEntity.ok(personFacade.updatePerson(id, editPersonDto));
    }

    @Override
    public ResponseEntity<Void> deletePersonById(String id) {
        personFacade.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
