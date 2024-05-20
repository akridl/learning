package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

    @Test
    void testGreetingEndpoint() {
        String name = "John Doe";

        given()
                .pathParam("name", name)
                .when().get("/hello/{name}")
                .then()
                .statusCode(200)
                .body(is("Hello " + name));
    }
}