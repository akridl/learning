package com.example.validationdemo.mapper;

import com.example.validationdemo.api.dto.PersonEditDto;
import com.example.validationdemo.api.dto.PersonViewDto;
import com.example.validationdemo.data.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PersonMapper {

    default IdMapper<Long> getIdMapper() {
        return new LongMapper();
    }

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonEditDto dto);

    PersonViewDto toDto(Person entity);
}
