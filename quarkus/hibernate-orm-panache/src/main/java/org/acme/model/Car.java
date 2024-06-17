package org.acme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Data
public class Car {

    @SequenceGenerator(name = "carSequence", sequenceName = "car_seq", initialValue = 100, allocationSize = 1)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carSequence")
    private Long id;

    private String brand;

    private String model;

    private int yearOfConstruction;
}
