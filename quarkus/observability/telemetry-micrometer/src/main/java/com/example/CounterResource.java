package com.example;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/counter")
public class CounterResource {

    @Inject
    MeterRegistry micrometerRegistry;

    @GET
    public String doSomething() {
        micrometerRegistry.counter("my-counters", "subdim", "foo", "dim", "bar").increment();
        // micrometerRegistry.counter("my-counters", "subdim", "foo", "dim", "baz").increment();

        Counter.builder("my-counters")
                .description("My counter for baz dimension")
                .tags("subdim", "foo", "dim", "baz")
                .register(micrometerRegistry);

        return "Counters increased (by 1)";
    }
}
