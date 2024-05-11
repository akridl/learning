package com.example.contact.mapper;

import com.example.contact.api.dto.ContactDto;
import com.example.contact.api.dto.ContactEditDto;
import com.example.contact.data.model.Contact;
import com.example.mapper.IdMapper;
import com.example.mapper.LongIdMapper;
import com.example.mapper.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class)
public interface ContactMapper {

    @Mapping(target = "id", ignore = true)
    Contact toEntity(ContactEditDto contactDto);

    ContactDto toDto(Contact contactEntity);

    default IdMapper<Long> getIdMapper() {
        return new LongIdMapper();
    }
}
