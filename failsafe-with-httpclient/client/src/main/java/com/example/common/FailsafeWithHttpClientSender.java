package com.example.common;

import com.example.dto.Callback;
import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
public class FailsafeWithHttpClientSender implements CallbackSender {

    @Inject
    ManagedExecutor executor;

    @Override
    public void sendCallback(Callback callback) {
        try (HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.of(2, ChronoUnit.SECONDS))
                .build()) {
            RetryPolicy<HttpResponse<String>> retryPolicy = RetryPolicy.<HttpResponse<String>>builder()
                    .withBackoff(1, 16, ChronoUnit.SECONDS)
                    .withMaxAttempts(8)
                    .onSuccess(e -> log.info("Callback successfully sent, response: {}", e.getResult()))
                    .onFailedAttempt(e -> log.warn("Attempt (#{}) failed", e.getAttemptCount()))
                    .onFailure(e -> log.warn("Failure: {}", e.getResult()))
                    .build();

            Failsafe.with(retryPolicy)
                    .with(executor)
                    .getAsync(() -> {
                        log.info("Sending the callback");
                        return httpClient.send(getNotifyHttpRequest(callback, ""), null);
                    })
                    .thenAccept(httpResponse -> log.info("Received: {}", httpResponse));
        }
    }

    private static HttpRequest getNotifyHttpRequest(Callback callback, String body) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(callback.getUrl()))
                .method(callback.getMethod(), HttpRequest.BodyPublishers.ofString(body))
                .build();
        log.info("Computed request: {}", request);
        return request;
    }
}
