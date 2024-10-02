package com.example;

import com.example.profiles.Profile1WithMock;
import com.example.utils.TestData;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusMainTest
@TestProfile(Profile1WithMock.class)
public class AppTest {

    @Test
    @Launch(value = TestData.NAME, exitCode = 0)
    void nameGiven_greetingPrinted(LaunchResult result) {
        assertThat(result.getOutput()).contains("Hola hola " + TestData.NAME);
        assertThat(result.exitCode()).isZero();
    }
}
