package com.example.person.data.model;

import com.example.person.exception.ContactAlreadyPresentException;
import com.example.person.exception.NoSuchContactException;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column
    private String givenName;

    @Column
    private String familyName;

    @Column(unique = true)
    @EqualsAndHashCode.Include
    private String email;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    @ElementCollection
    private Set<Long> contacts = new HashSet<>();

    public void addContact(Long contactId) {
        if (contacts.contains(contactId)) {
            throw new ContactAlreadyPresentException("Person with id=" + id + " has already assigned the contact with id=" + contactId + ".");
        }
        contacts.add(contactId);
    }

    public void removeContact(Long contactId) {
        if (!contacts.contains(contactId)) {
            throw new NoSuchContactException("Person with id=" + id + " does not have assigned the contact with id=" + contactId + ".");
        }
        contacts.remove(contactId);
    }
}
