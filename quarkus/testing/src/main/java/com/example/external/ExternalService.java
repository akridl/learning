package com.example.external;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@Path("/external")
@RegisterRestClient(baseUri = "http://example.com")  // Doesn't matter what the URI looks like, it will always be mocked anyway
public interface ExternalService {

    @GET
    String result();
}
