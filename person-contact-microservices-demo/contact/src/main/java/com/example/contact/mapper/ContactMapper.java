package com.example.contact.mapper;

import com.example.contact.api.dto.ContactDto;
import com.example.contact.api.dto.ContactRef;
import com.example.contact.data.model.Contact;
import com.example.mapper.CentralMapperConfig;
import com.example.mapper.IdMapper;
import com.example.mapper.LongIdMapper;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface ContactMapper {

    Contact toEntity(ContactRef contactDto);

    ContactDto toDto(Contact contactEntity);

    default IdMapper<Long> getIdMapper() {
        return new LongIdMapper();
    }
}
