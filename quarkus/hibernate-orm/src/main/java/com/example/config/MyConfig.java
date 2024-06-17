package com.example.config;

import io.smallrye.config.ConfigMapping;

import java.util.List;

@ConfigMapping(prefix = "my-config")
public interface MyConfig {

    PersistenceUnit persistenceUnit();

    interface PersistenceUnit {
        List<String> available();
    }
}
