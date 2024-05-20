package com.example;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
@Transactional
public class GreetingResource {

    @GET
    public String hello(@RestQuery String name) {
        String nameToGreet = (name == null) ? "anonymous" : name;

        Greeting greeting = new Greeting();
        greeting.name = nameToGreet;
        greeting.persist();

        return "Hello " + nameToGreet;
    }

    @GET
    @Path("/names")
    public String names(@RestQuery String nameRegex) {
        Pattern namePattern = (nameRegex == null) ? Pattern.compile(".*") : Pattern.compile(nameRegex);
        Set<String> names = Greeting.streamAll()
                .map(e -> (Greeting) e)
                .filter(g -> namePattern.matcher(g.name).matches())
                .map(g -> g.name)
                .collect(Collectors.toSet());
        return "I've already greeted: " + String.join(", ", names);
    }
}
