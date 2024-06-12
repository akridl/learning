package com.example.repository;

import com.example.model.Person;

public interface PersonRepository {

    Person createPerson(String name);

    Person getPersonById(Long id);
}
