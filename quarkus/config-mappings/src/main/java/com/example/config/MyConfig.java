package com.example.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithDefaults;
import io.smallrye.config.WithName;
import io.smallrye.config.WithParentName;
import io.smallrye.config.WithUnnamedKey;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Map;

@ConfigMapping(prefix = "my-config")
public interface MyConfig {

    @WithName("stuff")
    String superCoolStuff();

    // This has to be present, otherwise it is not linked
    Greeting greeting();

    // Instead of my-config.info.version, let's have it as my-config.version
    @WithParentName
    Info info();

    Numbers numbers();

    @WithName("environment")
    @WithDefaults
    @WithUnnamedKey("default")
    Map<String, EnvironmentConfig> environmentConfig();

    interface Greeting {
        @WithDefault("Hello")
        String greet();

        String name();
    }

    interface Info {
        String version();

        List<DeveloperContact> developers();
    }

    interface DeveloperContact {
        String name();

        String email();
    }

    interface Numbers {
        @WithName("int")
        int intPrimitive();

        @WithName("long")
        long longPrimitive();

        @WithName("float")
        float floatPrimitive();
    }
}
