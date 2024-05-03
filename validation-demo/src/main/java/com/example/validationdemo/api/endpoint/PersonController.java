package com.example.validationdemo.api.endpoint;

import com.example.validationdemo.api.dto.PersonEditDto;
import com.example.validationdemo.api.dto.PersonViewDto;
import com.example.validationdemo.facade.PersonFacade;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonFacade personFacade;

    public PersonController(PersonFacade personFacade) {
        this.personFacade = personFacade;
    }

    @PostMapping
    public PersonViewDto createPerson(@Valid @RequestBody PersonEditDto personEditDto) {
        return personFacade.createPerson(personEditDto);
    }

    @GetMapping("/{id}")
    public PersonViewDto getPersonById(@PathVariable String id) {
        return personFacade.getPersonById(id);
    }
}
