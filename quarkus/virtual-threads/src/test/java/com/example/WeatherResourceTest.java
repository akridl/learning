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
    void testGetAllWeathers() {
        given()
                .when().get("/weather")
                .then()
                .statusCode(200);
    }

    @Test
    void testGetBetterPlace() {
        given()
          .when().get("/weather/better-place")
          .then()
             .statusCode(200);
    }

    @Test
    void testGetBetterPlaceViaExecutor() {
        given()
                .when().get("/weather/better-place/via-executor")
                .then()
                .statusCode(200);
    }
}