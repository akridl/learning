package com.example.jpasandbox.rest;

import com.example.jpasandbox.api.dto.PersonDto;
import com.example.jpasandbox.api.endpoints.PersonController;
import com.example.jpasandbox.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonControllerImpl implements PersonController {


    private final PersonFacade personFacade;

    @Autowired
    public PersonControllerImpl(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @Override
    public PersonDto createPerson(PersonDto person) {
        System.out.println("Got person: " + person);
        return personFacade.createPerson(person);
    }

    @Override
    public Page<PersonDto> getAllPersons(int pageIndex, int pageSize) {
        return personFacade.getAllPersons(pageIndex, pageSize);
    }
}
