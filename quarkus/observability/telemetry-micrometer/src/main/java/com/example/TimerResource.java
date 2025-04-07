package com.example;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/timer")
public class TimerResource {

    private final Timer timerForDummy;
    private final Timer timerForWise;
    private final static String TIMER_NAME = "timer.sum";

    @Inject
    public TimerResource(MeterRegistry micrometerRegistry) {
        timerForDummy = Timer.builder(TIMER_NAME)
                .tag("type", "dummy")
                .register(micrometerRegistry);
        timerForWise = Timer.builder(TIMER_NAME)
                .description("Timer for summers")
                .tag("type", "wise")
                .register(micrometerRegistry);
    }

    @GET
    @Path("/sum/dummy/{n}")
    public String dummySum(@PathParam("n") int n) {
        int sum = timerForDummy.record(() -> computeSumDummy(n));

        return "dummy: " + sum;
    }

    @GET
    @Path("/sum/wise/{n}")
    public String wise(@PathParam("n") int n) {
        int sum = timerForWise.record(() -> computeSumWise(n));

        return "wisey: " + sum;
    }

    private int computeSumDummy(int n) {
        System.out.println("dummy summer");

        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    private int computeSumWise(int n) {
        System.out.println("wise summer");

        return n * (n + 1) / 2;
    }
}
