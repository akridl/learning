package com.example.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "callback-config")
public interface CallbackConfig {

    @WithName("retries")
    RetryConfig retryConfig();

    /**
     * All units are in seconds (except absolute ones, e.g. {@link RetryConfig#maxRetries()}.
     */
    interface RetryConfig {

        @WithDefault("1")
        int backoffInitialDelay();

        @WithDefault("30")
        int backoffMaxDelay();

        @WithDefault("-1")
        int maxRetries();

        @WithDefault("120")
        int maxDuration();
    }
}
