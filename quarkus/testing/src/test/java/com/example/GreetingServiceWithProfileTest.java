package com.example;

import com.example.external.ExternalService;
import com.example.profile.MyProfile;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@TestProfile(MyProfile.class)
public class GreetingServiceWithProfileTest {

    @Inject
    @RestClient
    ExternalService externalService;

    @Inject
    GreetingService service;

    @Test
    void value1() {
        // Overridden by profile, so not test-value-1 anymore
        assertThat(service.getValue1()).isEqualTo("profile-value-1");
    }

    @Test
    void value2() {
        assertThat(service.getValue2()).isEqualTo("test-value-2");
    }

    @Test
    void internalResult() {
        // using fake implementation
        assertThat(service.internalResult()).isEqualTo("Dummy result from fake internal service");
    }
}
