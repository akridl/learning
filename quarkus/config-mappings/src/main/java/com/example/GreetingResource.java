package com.example;

import com.example.config.MyConfig;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.function.Function;

@Path("/hello")
public class GreetingResource {

    @Inject
    MyConfig config;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return config.greeting().greet() + " " + config.greeting().name();
    }

    @GET
    @Path("/cool-stuff")
    public String superCoolStuff() {
        return config.superCoolStuff();
    }

    @GET
    @Path("/version")
    public String version() {
        return config.info().version();
    }

    @GET
    @Path("/developers")
    public List<DeveloperContact> developers() {
        return config.info().developers().stream()
                .map(d -> toDeveloper(d.name(), d.email()))
                .toList();
    }

    @GET
    @Path("/developers/name")
    public List<String> developersName() {
        return getDeveloperAttribute(MyConfig.DeveloperContact::name);
    }

    @GET
    @Path("/developers/email")
    public List<String> developersEmail() {
        return getDeveloperAttribute(MyConfig.DeveloperContact::email);
    }

    @GET
    @Path("/numbers")
    public Numbers numbers() {
        return Numbers.builder()
                .intNumber(config.numbers().intPrimitive())
                .longNumber(config.numbers().longPrimitive())
                .floatNumber(config.numbers().floatPrimitive())
                .build();
    }

    @GET
    @Path("/environments")
    public List<String> environments() {
        // Since, we've used @ConfigProperties, we can use more readable syntax
        // Instead of:
        return config.environmentConfig().keySet().stream().toList();
        // We can use:
        // return environments.keySet().stream().toList();
    }

    @GET
    @Path("/environments/{name}/pods-count")
    public Integer environmentPodsCount(@PathParam("name") String envName) {
        return config.environmentConfig().get(envName).podsActive();
    }

    private List<String> getDeveloperAttribute(Function<MyConfig.DeveloperContact, String> mappingFunction) {
        return config.info()
                .developers()
                .stream()
                .map(mappingFunction)
                .toList();
    }

    private DeveloperContact toDeveloper(String name, String email) {
        return new DeveloperContact(name, email);
    }
}
