package com.example;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
// Among others, this will result in Rest assured using the base path of the GreetingResource
// So, in particular, no need to specify /hello prefix in every test method
@TestHTTPEndpoint(GreetingResource.class)
class GreetingResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
          .when().get()
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

    @Test
    void testGreetingEndpoint() {
        String name = "John Doe";
        given()
                .pathParam("name", name)
                .when().get("/greeting/{name}")
                .then()
                .statusCode(200)
                .body(is("Hello " + name + "!"));
    }
}