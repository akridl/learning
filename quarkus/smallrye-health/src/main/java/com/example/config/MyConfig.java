package com.example.config;

import io.smallrye.config.ConfigMapping;

import java.util.Optional;

@ConfigMapping(prefix = "my-config")
public interface MyConfig {

    DatabaseConfig database();

    interface DatabaseConfig {
        String url();

        String username();

        String password();

        /**
         * Validation threshold in seconds.
         */
        int validationThreshold();
    }
}
