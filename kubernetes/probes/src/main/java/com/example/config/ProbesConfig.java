package com.example.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "probes")
public interface ProbesConfig {

    @WithName("ip")
    IpConfig ipConfig();

    interface IpConfig {
        String baseUri();
    }
}
