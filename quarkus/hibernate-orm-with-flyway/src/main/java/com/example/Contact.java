package com.example;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Contact extends PanacheEntity {

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Column(unique = true)
    private String value;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_person_contact"))
    private Person owner;
}
