package com.example.profiles;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Map;
import java.util.Set;

public class Profile2 implements QuarkusTestProfile {

    @Override
    public Set<String> tags() {
        return Set.of("profile-2");
    }

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of("quarkus.package.main-class", "main2");
    }
}
