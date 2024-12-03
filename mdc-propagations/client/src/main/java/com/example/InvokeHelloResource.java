package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.jboss.pnc.api.constants.MDCHeaderKeys;
import org.slf4j.MDC;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/invoke-hello")
@Slf4j
public class InvokeHelloResource {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String invokeHello() throws IOException, InterruptedException {
        MDC.put("foo", "bar");
        MDC.put("bar", "baz");

        MDC.put(MDCHeaderKeys.TMP.getHeaderName(), "tmp-foo");
        MDC.put(MDCHeaderKeys.PROCESS_CONTEXT.getHeaderName(), "process-foo");

        log.debug("MDC is: " + MDC.getCopyOfContextMap());

        // This is actually needed to propagate MDC, not done automatically
        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.noBody()).uri(URI.create("http://localhost:8090/hello")).headers(putMdcIntoHeaders()).build();

        // MDC is not propagated automatically through e.g. HTTP headers
        // HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.noBody()).uri(URI.create("http://localhost:8090/hello")).build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        log.debug("Response: status={}, payload={}", response.statusCode(), response.body());

        return "Hello successfully invoked";
    }

    private static String[] putMdcIntoHeaders() {
        List<String> headers = new ArrayList<>();

        MDC.getCopyOfContextMap().entrySet().forEach(entry -> {
            headers.add(entry.getKey());
            headers.add(entry.getValue());
        });

        log.debug("Headers: {}", headers);
        return headers.toArray(String[]::new);
    }
}
