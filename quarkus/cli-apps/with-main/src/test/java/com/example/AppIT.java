package com.example;

import com.example.profiles.Profile1WithoutMock;
import com.example.utils.TestData;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusMainTest
@TestProfile(Profile1WithoutMock.class)
public class AppIT {

    @Test
    @Launch(value = TestData.NAME, exitCode = 0)
    void nameGiven_greetingPrinted(LaunchResult result) {
        assertThat(result.getOutput()).contains("Hello " + TestData.NAME + "!");
        assertThat(result.exitCode()).isZero();
    }

    @Test
    @Launch(value = {}, exitCode = 1)
    void nameNotGiven_appFails(LaunchResult result) {
        assertThat(result.exitCode()).isOne();
    }
}
