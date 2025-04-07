package com.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/ignored")
public class IgnoredTagResource {

    private final Counter myCounter;

    @Inject
    public IgnoredTagResource(MeterRegistry micrometerRegistry) {
        myCounter = Counter.builder("ignored-counter")
                .description("This counter has 2 ignored tags, search 'ignored-tag' or 'yet-another-ignored-tag' in the project why is that so :/")
                .tags(Tags.of(
                                "ignored-tag", "whatever-its-ignored-anyway",
                                "yet-another-ignored-tag", "whatever-its-ignored-anyway",
                                "non-ignored-tag", "wohooo"
                        ))
                .register(micrometerRegistry);
    }

    @GET
    public String doSomething() {
        myCounter.increment();
        return "done";
    }
}
