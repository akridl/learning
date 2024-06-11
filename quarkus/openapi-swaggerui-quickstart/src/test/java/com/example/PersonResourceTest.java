package com.example;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
class PersonResourceTest {

    @Inject
    PersonResource personResource;

    @BeforeEach
    void setUp() {
        personResource.create(new Person("John", "Doe", 42));
        personResource.create(new Person("Jane", "Doe", 41));
    }

    @Test
    void testList() {
        given()
          .when().get("/persons")
          .then()
             .statusCode(200)
             .body("$.size()", is(2),
                     "givenName", containsInAnyOrder("John", "Jane"),
                     "familyName", containsInAnyOrder("Doe", "Doe"),
                     "age", containsInAnyOrder(41, 42));
    }
}