package com.example;

import com.example.internal.InternalService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class InternalServiceTest {

    @InjectMock
    InternalService internalService;

    @BeforeEach
    void setup() {
        Mockito.when(internalService.result()).thenReturn("Result from mock");
    }

    @Test
    void result() {
        assertThat(internalService.result()).isEqualTo("Result from mock");
    }
}
