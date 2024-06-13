package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testGreetingEndpoint() {
        given()
          .when().get("/greeting")
          .then()
             .statusCode(200)
             .body(is("hi test!"));
    }

    @Test
    void testUserSecretEndpoint() {
        given()
                .when().get("/greeting/user-secret")
                .then()
                .statusCode(200)
                .body(containsString("Secret is"));
    }
}