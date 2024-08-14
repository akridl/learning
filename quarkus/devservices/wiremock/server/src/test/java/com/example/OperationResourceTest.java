package com.example;

import com.example.dto.AdjustResponse;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestHTTPEndpoint(OperationResource.class)
class OperationResourceTest {

    @Test
    void completeAdjust() {
        given().when().header("Content-Type", MediaType.APPLICATION_JSON).header("Accept", MediaType.TEXT_PLAIN).body(AdjustResponse.builder().text("test").build()).post("/adjust/complete").then().statusCode(200).body(is("Adjustment completed"));
    }
}
