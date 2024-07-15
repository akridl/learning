package com.example;

import com.example.internal.InternalService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class InternalServiceMockVsSpyTest {

    @InjectMock
    InternalService internalServiceMock;

    @InjectSpy
    InternalService internalServiceSpy;

    @Test
    void testResultOnMock() {
        // When mock is not stubbed, Mockito returns its defaults -- for Object, it's null
        assertThat(internalServiceMock.result()).isNull();
    }

    @Test
    void testResultOnSpy() {
        // When spy is not stubbed, the behavior is as called on a real object
        assertThat(internalServiceSpy.result()).isEqualTo("Dummy result from fake internal service");
    }
}
