package com.example.jpasandbox.mapper;

import com.example.jpasandbox.api.dto.PersonDto;
import com.example.jpasandbox.data.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PersonMapper extends EntityMapper<PersonDto, Person> {

    @Override
    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonDto personDto);

    @Override
    PersonDto fromEntity(Person entity);
}
