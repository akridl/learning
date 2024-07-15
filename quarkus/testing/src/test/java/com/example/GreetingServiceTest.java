package com.example;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class GreetingServiceTest {

    @Inject
    GreetingService service;

    @Test
    void greeting() {
        assertThat(service.greeting("John")).isEqualTo("Hello John!");
    }

    @Test
    void internalResult() {
        // using real implementation
        assertThat(service.internalResult()).isEqualTo("Dummy result from fake internal service");
        // assertThat(service.internalResult()).isEqualTo("Result from super-complicated implementation");
    }

    @Test
    void value1() {
        assertThat(service.getValue1()).isEqualTo("test-value-1");
    }

    @Test
    void value2() {
        assertThat(service.getValue2()).isEqualTo("test-value-2");
    }
}