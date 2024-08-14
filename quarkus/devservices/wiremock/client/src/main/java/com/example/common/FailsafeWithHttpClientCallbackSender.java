package com.example.common;

import com.example.config.CallbackConfig;
import com.example.dto.AdjustResponse;
import com.example.dto.Callback;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.context.ManagedExecutor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
@Slf4j
public class FailsafeWithHttpClientCallbackSender implements CallbackSender {

    private final ManagedExecutor executor;
    private final CallbackConfig config;
    private final ObjectMapper objectMapper;

    public FailsafeWithHttpClientCallbackSender(ManagedExecutor executor, CallbackConfig config, ObjectMapper objectMapper) {
        this.executor = executor;
        this.config = config;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendCallback(Callback callback, String adjustedText) {
        RetryPolicy<HttpResponse<String>> retryPolicy = RetryPolicy.<HttpResponse<String>>builder()
                .withBackoff(config.retryConfig().backoffInitialDelay(), config.retryConfig().backoffMaxDelay(),
                        ChronoUnit.SECONDS)
                .withMaxRetries(config.retryConfig().maxRetries())
                .withMaxDuration(Duration.of(config.retryConfig().maxDuration(), ChronoUnit.SECONDS))
                .onSuccess(e -> log.info("Callback successfully sent, response: {}", e.getResult().statusCode()))
                .onRetry(e -> log.warn("Retrying (attempt #{}), last response was: {}", e.getAttemptCount(), e.getLastException().toString()))
                .onFailure(e -> log.warn("Callback couldn't be sent: {}", e.getException().toString()))
                .build();

        Failsafe.with(retryPolicy).with(executor).runAsync(() -> {
            try (HttpClient httpClient = HttpClient.newHttpClient()) {
                log.info("Sending the callback..");
                httpClient.sendAsync(getCallbackHttpRequest(callback, adjustedText),
                        HttpResponse.BodyHandlers.ofString())
                        .thenAccept(response -> log.info("Got response: statusCode={}, body={}", response.statusCode(), response.body()));
            }
        });
    }

    private HttpRequest getCallbackHttpRequest(Callback callback, String adjustedText) {
        try {
            String adjustResponseJson = objectMapper.writeValueAsString(AdjustResponse.builder().text(adjustedText).build());
            return HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(callback.getMethod(), HttpRequest.BodyPublishers.ofString(adjustResponseJson))
                    .uri(URI.create(callback.getUrl()))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
