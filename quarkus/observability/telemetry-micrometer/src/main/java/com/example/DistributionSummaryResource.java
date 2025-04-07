package com.example;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/distribution-summary")
public class DistributionSummaryResource {

    private static final String DISTRIBUTION_SUMMARY_NAME = "my-ds.response.size";
    private final DistributionSummary responseSizesSummary;

    @Inject
    public DistributionSummaryResource(MeterRegistry micrometerRegistry) {
        responseSizesSummary = DistributionSummary.builder(DISTRIBUTION_SUMMARY_NAME)
                .description("Distribution summary for response sizes")
                .tag("protocol", "http")
                .register(micrometerRegistry);
    }

    @Path("/hello")
    @POST
    public String sayHello(@QueryParam("name") String name) {
        if (name == null) {
            name = "anonymous";
        }

        String response = String.format("Hello %s!", name);
        responseSizesSummary.record(response.length());

        return response;
    }
}
