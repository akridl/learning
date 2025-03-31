package com.example.jpasandbox.rest;

import com.example.jpasandbox.data.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping
@Slf4j
public class MyRestController {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostMapping("/do-something")
    @Transactional
    public ResponseEntity<String> doSomething() {
        log.info("doing something");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Person person = new Person();
        person.setGivenName("John");
        person.setFamilyName("Doe");
        person.setBirthday(LocalDate.of(2002, Month.FEBRUARY, 2));
        log.info("Created person: {}", person);

        log.info("Storing..");
        em.persist(person);
        em.getTransaction().commit();

        em.close();

        return ResponseEntity.ok("Successfully finished");
    }
}
