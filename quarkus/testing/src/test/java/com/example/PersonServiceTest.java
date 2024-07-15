package com.example;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class PersonServiceTest {

    @Inject
    PersonService service;

    @Test
    @Transactional  // This is **typically** wrong, since it uses @Transactional, which results in persistent storage.
    void savePersonWrong() {
        String givenName = "Scotty";
        String familyName = "White";
        LocalDate birthday = LocalDate.of(2002, Month.FEBRUARY, 2);
        Person person = new Person();
        person.setGivenName(givenName);
        person.setFamilyName(familyName);
        person.setBirthday(birthday);

        Person savedPerson = service.savePerson(person);

        assertThat(savedPerson.getGivenName()).isEqualTo(givenName);
        assertThat(savedPerson.getFamilyName()).isEqualTo(familyName);
        assertThat(savedPerson.getBirthday()).isEqualTo(birthday);
    }

    @Test
    @TestTransaction
    void savePersonCorrect() {
        String givenName = "Colton";
        String familyName = "Green";
        LocalDate birthday = LocalDate.of(2001, Month.JANUARY, 1);
        Person person = new Person();
        person.setGivenName(givenName);
        person.setFamilyName(familyName);
        person.setBirthday(birthday);

        Person savedPerson = service.savePerson(person);

        assertThat(savedPerson.getGivenName()).isEqualTo(givenName);
        assertThat(savedPerson.getFamilyName()).isEqualTo(familyName);
        assertThat(savedPerson.getBirthday()).isEqualTo(birthday);
    }
}