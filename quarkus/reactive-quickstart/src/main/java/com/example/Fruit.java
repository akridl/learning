package com.example;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Fruit extends PanacheEntity {

    @Column(unique = true)
    public String name;
}
