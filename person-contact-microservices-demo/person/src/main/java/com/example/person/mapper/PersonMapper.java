package com.example.person.mapper;

import com.example.client_model.ContactRef;
import com.example.mapper.CentralMapperConfig;
import com.example.mapper.IdMapper;
import com.example.mapper.LongIdMapper;
import com.example.person.api.dto.PersonDto;
import com.example.person.api.dto.PersonRef;
import com.example.person.data.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = CentralMapperConfig.class)
public interface PersonMapper {

    @Mapping(target = "contacts", ignore = true)
    Person toEntity(PersonRef personDto);

    @Mapping(target = "contacts", qualifiedByName = "toContact")
    PersonDto toDto(Person person);

    default IdMapper<Long> getIdMapper() {
        return new LongIdMapper();
    }

    @Named("toContact")
    default ContactRef toContact(Long id) {
        ContactRef contact = new ContactRef();
        contact.setId(String.valueOf(id));
        return contact;
    }
}
