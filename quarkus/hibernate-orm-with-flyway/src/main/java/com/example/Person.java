package com.example;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Person extends PanacheEntity {

    private String givenName;

    private String familyName;

    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

    @OneToMany(mappedBy = "owner")
    private Set<Contact> contacts;
}
