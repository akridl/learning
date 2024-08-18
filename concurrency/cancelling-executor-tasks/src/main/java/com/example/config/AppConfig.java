package com.example.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app-config")
public interface AppConfig {

    OperationsConfig operations();
}
