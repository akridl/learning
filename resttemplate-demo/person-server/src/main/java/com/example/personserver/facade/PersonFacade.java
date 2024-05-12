package com.example.personserver.facade;

import com.example.personserver.api.dto.PersonDto;
import com.example.personserver.api.dto.PersonFilter;
import com.example.personserver.mapper.PersonMapper;
import com.example.personserver.service.PersonService;
import com.example.personserver.validation.PersonValidator;
import com.example.personserver.validation.group.WhenCreating;
import com.example.personserver.validation.group.WhenUpdating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonFacade {

    private final PersonService personService;

    private final PersonMapper personMapper;

    @Autowired
    public PersonFacade(
            PersonService personService,
            PersonMapper personMapper
    ) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    public PersonDto createPerson(PersonDto createPersonDto) {
        PersonValidator.validatePerson(createPersonDto, WhenCreating.class);
        return personMapper.toDto(personService.createPerson(personMapper.toEntity(createPersonDto)));
    }

    public PersonDto findPersonById(String id) {
        return personMapper.toDto(personService.findPersonById(personMapper.getIdMapper().toEntityId(id)));
    }

    public Page<PersonDto> findAll(PersonFilter personFilter) {
        return personMapper.toDtoPage(personService.findAll(personFilter));
    }

    public PersonDto updatePerson(String id, PersonDto editPersonDto) {
        PersonValidator.validatePerson(editPersonDto, WhenUpdating.class);
        return personMapper.toDto(personService.updatePerson(personMapper.getIdMapper().toEntityId(id), personMapper.toEntity(editPersonDto)));
    }

    public void deletePerson(String id) {
        personService.deletePersonById(personMapper.getIdMapper().toEntityId(id));
    }
}
