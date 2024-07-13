package com.example.service;

import com.example.model.Location;
import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "ip-api")
public interface IpService {

    @GET
    Location getLocation();
}
