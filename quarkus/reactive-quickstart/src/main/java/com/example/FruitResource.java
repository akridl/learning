package com.example;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/fruits")
public class FruitResource {

    @POST
    public Uni<RestResponse<Fruit>> createFruit(Fruit fruit) {
        return Panache.withTransaction(fruit::persist).replaceWith(RestResponse.status(RestResponse.Status.CREATED, fruit));
    }

    @GET
    @Path("/{id}")
    public Uni<Fruit> getFruitById(@PathParam("id") Long id) {
        return Fruit.findById(id);
    }

    @GET
    public Uni<List<Fruit>> getAllFruits() {
        return Fruit.listAll();
    }
}
