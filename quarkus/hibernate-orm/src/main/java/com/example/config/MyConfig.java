package com.example.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "my-config")
public interface MyConfig {

    String persistenceUnitName();
}
