package com.example.rest.endpoint;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class GreetingResourceTest {

    @Test
    void hello_whenAnonymous_accessGranted() {
        given().header("accept", MediaType.APPLICATION_JSON).when()
                        .get("/hello")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    void admin_whenAnonymous_returnsNotAuthenticated() {
        given().header("accept", MediaType.TEXT_PLAIN).when()
                .get("/hello/admin")
                .then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void admin_whenAdmin_accessGranted() {
        given()
                .auth().preemptive().basic("Admin", "admin").and()
                .header("accept", MediaType.TEXT_PLAIN).when()
                .get("/hello/admin")
                .then().statusCode(HttpStatus.SC_OK);
    }
}