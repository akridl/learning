package com.example.contact.api.endpoint.impl;

import com.example.contact.api.dto.ContactDto;
import com.example.contact.api.dto.ContactRef;
import com.example.contact.api.endpoint.ContactController;
import com.example.contact.facade.ContactFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactControllerImpl implements ContactController {

    private final ContactFacade contactFacade;

    public ContactControllerImpl(ContactFacade contactFacade) {
        this.contactFacade = contactFacade;
    }

    @Override
    public ResponseEntity<ContactDto> createContact(ContactRef createContactDto) {
        return new ResponseEntity<>(contactFacade.createContact(createContactDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ContactDto> getContactById(String id) {
        return ResponseEntity.ok(contactFacade.getContactById(id));
    }

    @Override
    public ResponseEntity<Void> deleteContactById(String id) {
        contactFacade.deleteContactById(id);
        return ResponseEntity.noContent().build();
    }
}
