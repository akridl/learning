package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class Person extends PanacheEntity {

    public String givenName;

    public String familyName;

    public LocalDate birthday;
}
