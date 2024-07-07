package com.example;

import io.quarkus.virtual.threads.VirtualThreads;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Path("/weather")
public class WeatherResource {

    private static final double VALENCIA_LATITUDE = 39.4697;
    private static final double VALENCIA_LONGITUDE = -0.3774;

    private static final double ATHENS_LATITUDE = 37.9838;
    private static final double ATHENS_LONGITUDE = 23.7278;

    @Inject
    @VirtualThreads
    ExecutorService executorService;

    @RestClient
    WeatherService service;

    @GET
    @RunOnVirtualThread
    public String getBetterPlace() {
        return getBetterCity();
    }

    @GET
    @Path("/via-executor")
    public String getBetterPlaceViaExecutor() throws ExecutionException, InterruptedException {
        return executorService.submit(this::getBetterCity).get();
    }

    private String getBetterCity() {
        double valenciaTemp = service.getWeather(VALENCIA_LATITUDE, VALENCIA_LONGITUDE)
                .weather()
                .temperature();
        double athensTemp = service.getWeather(ATHENS_LATITUDE, ATHENS_LONGITUDE)
                .weather()
                .temperature();

        if (valenciaTemp > athensTemp && valenciaTemp <= 35) {
            return getResultingString("Valencia");
        }
        return getResultingString("Athens");
    }

    private static String getResultingString(String city) {
        return city + " " + Thread.currentThread() + "!";
    }
}
