package com.example;

import com.example.dto.AdjustRequest;
import com.example.dto.Callback;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkiverse.wiremock.devservice.ConnectWireMock;
import io.quarkiverse.wiremock.devservice.WireMockConfigKey;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@ConnectWireMock
class AdjustResourceTest {

    @ConfigProperty(name = WireMockConfigKey.PORT)
    Integer port;

    WireMock serverWiremock;

    @Test
    void completeAdjust() throws InterruptedException {
        System.out.println("********** Port is: " + port);

        given().when()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("Accept", MediaType.TEXT_PLAIN)
                .body(AdjustRequest.builder()
                        .text("test")
                        .callback(Callback.builder()
                                .method("POST")
                                .url("http://localhost:" + port + "/operation/adjust/complete")
                                .build())
                        .build())
                .post("/adjust")
                .then()
                .statusCode(204);

        serverWiremock.verifyThat(1, WireMock.postRequestedFor(WireMock.urlEqualTo("/operation/adjust/complete")).withHeader("Content-Type", WireMock.equalTo(MediaType.APPLICATION_JSON)));
    }
}
