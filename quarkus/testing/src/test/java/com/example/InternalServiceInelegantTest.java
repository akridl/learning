package com.example;

import com.example.internal.InternalService;
import com.example.internal.InternalServiceImpl;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class InternalServiceInelegantTest {

    /*
        Inelegant, because we need to use both Mockito.mock() and also QuarkusMock.installMockFor*().
        This can be out of the box handled by @InjectMock quarkus annotation, so tests written that way are cleaner.
     */

    @Inject
    InternalService internalService;

    @BeforeAll
    static void setup() {
        InternalService internalServiceMock = Mockito.mock(InternalServiceImpl.class);
        Mockito.when(internalServiceMock.result()).thenReturn("Result from mock");
        QuarkusMock.installMockForType(internalServiceMock, InternalService.class);
    }

    @Test
    void result() {
        assertThat(internalService.result()).isEqualTo("Result from mock");
    }
}
