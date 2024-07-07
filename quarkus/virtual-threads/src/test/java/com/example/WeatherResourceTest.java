package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit5.virtual.ShouldNotPin;
import io.quarkus.test.junit5.virtual.VirtualThreadUnit;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@VirtualThreadUnit
@ShouldNotPin
class WeatherResourceTest {

    @Test
    void testWeatherEndpoint() {
        given()
          .when().get("/weather")
          .then()
             .statusCode(200);
    }

    @Test
    void testWeatherEndpointViaExecutor() {
        given()
                .when().get("/weather/via-executor")
                .then()
                .statusCode(200);
    }
}