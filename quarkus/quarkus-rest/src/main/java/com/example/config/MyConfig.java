package com.example.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "my-config")
public interface MyConfig {

    @WithName("person")
    PersonConfig personConfig();
}
