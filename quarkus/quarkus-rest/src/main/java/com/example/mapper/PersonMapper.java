package com.example.mapper;

import com.example.api.dto.EditPersonDTO;
import com.example.api.dto.PersonDTO;
import com.example.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person toEntity(EditPersonDTO dto);

    PersonDTO toDTO(Person entity);
}
