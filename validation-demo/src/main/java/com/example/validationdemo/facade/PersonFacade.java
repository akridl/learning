package com.example.validationdemo.facade;

import com.example.validationdemo.api.dto.PersonEditDto;
import com.example.validationdemo.api.dto.PersonViewDto;
import com.example.validationdemo.mapper.PersonMapper;
import com.example.validationdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonFacade {

    private final PersonMapper personMapper;
    private final PersonService personService;

    @Autowired
    public PersonFacade(
            PersonMapper personMapper,
            PersonService personService
    ) {
        this.personMapper = personMapper;
        this.personService = personService;
    }

    public PersonViewDto createPerson(PersonEditDto personEditDto) {
        return personMapper.toDto(personService.createPerson(personMapper.toEntity(personEditDto)));
    }

    @Transactional(readOnly = true)
    public PersonViewDto getPersonById(String id) {
        return personMapper.toDto(personService.getPersonById(personMapper.getIdMapper().toDomainId(id)));
    }
}
