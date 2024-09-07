package com.example;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class FruitResourceTest {

    @Test
    void testCreateFruit() {
        Fruit fruit = new Fruit();
        fruit.name = "Pineapple";

        given()
                .when().header("Content-Type", MediaType.APPLICATION_JSON).body(fruit).post("/fruits")
                .then().statusCode(201)
                .body("id", Matchers.equalTo(3))
                .body("name", Matchers.equalTo("Pineapple"));
    }

    @Test
    void testGetFruitByIdWhenFound() {
        given()
                .when().get("/fruits/1")
                .then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Apple"));
    }

    @Test
    void testGetFruitByIdWhenNotFound() {
        given()
                .when().get("/fruits/999")
                .then().statusCode(204);
    }

    @Test
    void testGetAllFruits() {
        List<Fruit> fruits = Arrays.asList(given()
          .when().get("/fruits")
          .then().statusCode(200)
                .extract()
                .as(Fruit[].class));

        assertThat(fruits).hasSize(2);
    }
}