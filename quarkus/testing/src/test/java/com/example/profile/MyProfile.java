package com.example.profile;

import com.example.internal.InternalService;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.ws.rs.Produces;

import java.util.Map;

public class MyProfile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of("my-config.key1", "profile-value-1");
    }

    // Mock support using @Alternative
    @Produces @Alternative @Priority(1)
    @ApplicationScoped
    public InternalService internalService() {
        return new InternalServiceFake();
    }

    public static class InternalServiceFake implements InternalService {

        @Override
        public String result() {
            return "Dummy result from fake internal service";
        }
    }
}
