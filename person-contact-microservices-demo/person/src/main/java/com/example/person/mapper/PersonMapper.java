package com.example.person.mapper;

import com.example.mapper.CentralMapperConfig;
import com.example.mapper.IdMapper;
import com.example.mapper.LongIdMapper;
import com.example.person.api.dto.PersonDto;
import com.example.person.api.dto.PersonRef;
import com.example.person.data.model.Person;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface PersonMapper {

    Person toEntity(PersonRef personDto);

    PersonDto toDto(Person person);

    default IdMapper<Long> getIdMapper() {
        return new LongIdMapper();
    }
}
