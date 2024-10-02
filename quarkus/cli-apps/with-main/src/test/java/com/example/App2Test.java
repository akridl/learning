package com.example;

import com.example.profiles.Profile2;
import com.example.utils.TestData;
import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusMainTest
@TestProfile(Profile2.class)
public class App2Test {

    @Test
    @Launch(TestData.NAME)
    void hiNamePrinted(LaunchResult result) {
        assertThat(result.getOutput()).contains("Hi " + TestData.NAME);
    }
}
