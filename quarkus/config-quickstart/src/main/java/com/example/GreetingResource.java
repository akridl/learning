package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@Path("/greeting")
public class GreetingResource {

    @ConfigProperty(name = "greeting.message")
    String message;

    // When property with such a name is not found anywhere
    // (system variable, env variable, application.[properties|yml], ..),
    // the DeploymentException is thrown, we can prevent it by providing default value
    @ConfigProperty(name = "greeting.suffix", defaultValue = "!")
    String suffix;

    // Another way to prevent exception is to use Optional
    @ConfigProperty(name = "greeting.name")
    Optional<String> name;

    @ConfigProperty(name = "USER_SECRET")
    Optional<String> secret;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message + " " + name.orElse("anonymous") + suffix;
    }

    @GET
    @Path("/user-secret")
    public String secret() {
        return "Secret is: " + secret.orElseThrow(() -> new SecretNotDefinedException("The .env file is not present in the root directory. Go on, and copy it from .env.example."));
    }
}
