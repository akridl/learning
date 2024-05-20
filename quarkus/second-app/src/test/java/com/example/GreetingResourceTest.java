package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpointAnonymous() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello anonymous"));
    }

    @Test
    void testHelloEndpointWithName() {
        String name = "John Doe";

        given()
                .queryParam("name", name)
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello " + name));
    }
}