package com.example;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("/weather")
public class WeatherResource {

    private final WeatherFacade weatherFacade;

    @Inject
    public WeatherResource(WeatherFacade weatherFacade) {
        this.weatherFacade = weatherFacade;
    }

    @GET
    @RunOnVirtualThread
    public List<Weather> getAllWeathers() {
        return weatherFacade.getAllWeathers();
    }

    @GET
    @Path("/better-place")
    @RunOnVirtualThread
    public String getBetterPlace() {
        return weatherFacade.getBetterPlace();
    }

    @GET
    @Path("/better-place/via-executor")
    public String getBetterPlaceViaExecutor() throws ExecutionException, InterruptedException {
        return weatherFacade.getBetterPlaceViaExecutor();
    }
}
