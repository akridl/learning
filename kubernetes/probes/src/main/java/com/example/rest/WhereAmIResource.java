package com.example.rest;

import com.example.model.Location;
import com.example.service.IpService;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/whereami")
@Slf4j
public class WhereAmIResource {

    @RestClient
    IpService ipService;

    @GET
    @RunOnVirtualThread
    public Location whereami() {
        log.info("Calling the ip API through the REST client...");
        return ipService.getLocation();
    }
}
