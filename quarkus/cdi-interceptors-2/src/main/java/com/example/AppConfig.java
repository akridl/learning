package com.example;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app-config")
public interface AppConfig {

    String userLoggerName();
}
