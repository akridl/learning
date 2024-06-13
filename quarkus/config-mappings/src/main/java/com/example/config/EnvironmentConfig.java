package com.example.config;

import io.smallrye.config.WithDefault;

public interface EnvironmentConfig {

    @WithDefault("localhost")
    String url();

    @WithDefault("0")
    int podsActive();
}
