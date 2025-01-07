package com.example.rest.endpoint;

import com.example.model.User;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class UserResourceTest {

    @BeforeAll
    @Transactional
    static void beforeAll() {
        var user = new User("User", "user", List.of("roles"));
        user.add();
    }

    @Test
    void getUserInfo_whenAnonymous_returnsNotAuthenticated() {
        given().header("accept", MediaType.TEXT_PLAIN).when()
                .get("/users/me")
                .then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    void getUserInfo_whenUser_accessGranted() {
        given()
                .auth().preemptive().basic("User", "user").and()
                .header("accept", MediaType.TEXT_PLAIN).when()
                .get("/users/me")
                .then().statusCode(HttpStatus.SC_OK)
                .body(Matchers.is("Hello User!"));
    }
}