package com.example.config;

import io.smallrye.config.WithDefault;

public interface OperationsConfig {

    Duration duration();

    interface Duration {

        @WithDefault("10")
        int runtime();

        @WithDefault("10")
        int scheduleIn();
    }
}
