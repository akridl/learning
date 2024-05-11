package com.example.contact.facade;

import com.example.contact.api.dto.ContactDto;
import com.example.contact.api.dto.ContactRef;
import com.example.contact.mapper.ContactMapper;
import com.example.contact.service.ContactService;
import com.example.contact.validation.ContactValidator;
import com.example.validation.WhenCreating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactFacade {

    private final ContactService contactService;

    private final ContactMapper contactMapper;

    @Autowired
    public ContactFacade(
            ContactService contactService,
            ContactMapper contactMapper
    ) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    public ContactDto createContact(ContactRef contactEditDto) {
        ContactValidator.validateContact(contactEditDto, WhenCreating.class);
        return contactMapper.toDto(contactService.createContact(contactMapper.toEntity(contactEditDto)));
    }

    public ContactDto getContactById(String id) {
        return contactMapper.toDto(contactService.getContactById(contactMapper.getIdMapper().toEntityId(id)));
    }

    public void deleteContactById(String id) {
        contactService.deleteContactById(contactMapper.getIdMapper().toEntityId(id));
    }
}
