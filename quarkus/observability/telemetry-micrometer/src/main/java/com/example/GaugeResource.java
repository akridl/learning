package com.example;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

@Path("/gauges")
public class GaugeResource {

    private final List<Integer> queue = new ArrayList<>();

    private final static String GAUGE_NAME = "queue";

    @Inject
    public GaugeResource(MeterRegistry micrometerRegistry) {
        // micrometerRegistry.gaugeCollectionSize(GAUGE_NAME, Tags.empty(), queue);

        Gauge.builder(GAUGE_NAME, this, this::getQueueSize)
                .baseUnit("gauges")
                .description("gauges queue")
                .register(micrometerRegistry);
    }

    private double getQueueSize(GaugeResource gaugeResource) {
        return gaugeResource.queue.size();
    }

    @POST
    @Path("/add")
    public String add() {
        queue.add(queue.size());
        return "added";
    }

    @POST
    @Path("/remove")
    public String remove() {
        if (queue.isEmpty()) {
            return "queue is already empty";
        }

        queue.removeLast();
        return "removed";
    }
}
