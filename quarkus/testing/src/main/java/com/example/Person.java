package com.example;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Person extends PanacheEntity {

    @NotBlank
    @Length(min = 2)
    private String givenName;

    @NotBlank
    @Length(min = 3)
    private String familyName;

    @Temporal(TemporalType.DATE)
    private LocalDate birthday;
}
