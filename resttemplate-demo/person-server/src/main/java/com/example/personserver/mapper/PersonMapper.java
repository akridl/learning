package com.example.personserver.mapper;

import com.example.personserver.api.dto.PersonDto;
import com.example.personserver.data.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(config = CentralMapperConfig.class)
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person toEntity(PersonDto personDto);

    PersonDto toDto(Person personEntity);

    default Page<PersonDto> toDtoPage(Page<Person> entityPage) {
        return entityPage.map(this::toDto);
    }

    default IdMapper<Long> getIdMapper() {
        return new LongIdMapper();
    }
}
