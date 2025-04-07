package com.example;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.quarkus.micrometer.runtime.MeterFilterConstraint;
import io.quarkus.micrometer.runtime.MeterRegistryCustomizer;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

/**
 * When {@link io.micrometer.core.instrument.MeterRegistry} instance(s) will be instantiated,
 * {@link io.micrometer.core.instrument.config.MeterFilter} CDI beans will be used automatically.
 */
@Singleton
public class CustomMeterRegistryCustomizers {

    @Produces
    @Singleton
    @MeterFilterConstraint(applyTo = PrometheusMeterRegistry.class)
    public MeterFilter automaticallyApplyPrometheusTags() {
        return MeterFilter.commonTags(Tags.of("registry", "prometheus"));
    }

    @Produces
    @Singleton
    public MeterFilter ignoreTags() {
        return MeterFilter.ignoreTags("ignored-tag");
    }

    @Produces
    @Singleton
    public MeterRegistryCustomizer customizeMicrometerRegistry() {
        return new MeterRegistryCustomizer() {
            @Override
            public void customize(MeterRegistry registry) {
                registry.config().meterFilter(MeterFilter.ignoreTags("yet-another-ignored-tag"));
            }

            @Override
            public int priority() {
                return MeterRegistryCustomizer.DEFAULT_PRIORITY;
            }
        };
    }
}
