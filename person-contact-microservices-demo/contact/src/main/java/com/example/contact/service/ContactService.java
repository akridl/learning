package com.example.contact.service;

import com.example.contact.data.model.Contact;
import com.example.contact.data.repository.ContactRepository;
import com.example.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Transactional(readOnly = true)
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> createNotFoundException(id));
    }

    public void deleteContactById(Long id) {
        if (contactRepository.findById(id).isEmpty()) {
            throw createNotFoundException(id);
        }
        contactRepository.deleteById(id);
    }

    private static ResourceNotFoundException createNotFoundException(Long id) {
        return new ResourceNotFoundException("Contact with id=" + id + " not found.");
    }
}
