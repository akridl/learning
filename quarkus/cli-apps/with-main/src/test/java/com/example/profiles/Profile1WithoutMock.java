package com.example.profiles;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;
import java.util.Set;

public class Profile1WithoutMock implements QuarkusTestProfile {

    @Override
    public Set<String> tags() {
        return Set.of("profile-1");
    }

    @Override
    public Map<String, String> getConfigOverrides() {
        // OFC, this could be set in application-test.properties / %test...
        return Map.of("quarkus.package.main-class", "main1");
    }
}
