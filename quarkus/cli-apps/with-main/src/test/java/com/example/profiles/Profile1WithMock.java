package com.example.profiles;

import com.example.mocks.GreetingServiceMock;
import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.Set;

public class Profile1WithMock extends Profile1WithoutMock implements QuarkusTestProfile {

    @Override
    public Set<Class<?>> getEnabledAlternatives() {
        return Set.of(GreetingServiceMock.class);
    }
}
