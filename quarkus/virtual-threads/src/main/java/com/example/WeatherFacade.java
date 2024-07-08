package com.example;

import io.quarkus.virtual.threads.VirtualThreads;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@ApplicationScoped
@Slf4j
public class WeatherFacade {

    private static final double VALENCIA_LATITUDE = 39.4697;
    private static final double VALENCIA_LONGITUDE = -0.3774;

    private static final double ATHENS_LATITUDE = 37.9838;
    private static final double ATHENS_LONGITUDE = 23.7278;

    @VirtualThreads
    ExecutorService executorService;

    @RestClient
    WeatherService weatherService;

    public List<Weather> getAllWeathers() {
        log.info("Current thread: {}", Thread.currentThread());
        return Weather.listAll();
    }

    public String getBetterPlace() {
        return getBetterCity();
    }

    public String getBetterPlaceViaExecutor() throws ExecutionException, InterruptedException {
        return executorService.submit(this::getBetterCity).get();
    }

    private String getBetterCity() {
        double valenciaTemp = weatherService.getWeather(VALENCIA_LATITUDE, VALENCIA_LONGITUDE)
                .weather()
                .temperature();
        double athensTemp = weatherService.getWeather(ATHENS_LATITUDE, ATHENS_LONGITUDE)
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
