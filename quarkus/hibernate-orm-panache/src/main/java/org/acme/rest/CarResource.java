package org.acme.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.acme.model.Car;
import org.acme.repository.CarRepository;

import java.util.List;

@Path("/cars")
public class CarResource {

    private final CarRepository carRepository;

    @Inject
    public CarResource(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @POST
    public Car createCar(CreateCarDto createCarDto) {
        Car car = new Car();
        car.setBrand(createCarDto.getBrand());
        car.setModel(createCarDto.getModel());
        car.setYearOfConstruction(createCarDto.getYearOfConstruction());
        carRepository.persist(car);
        return car;
    }

    @GET
    @Path("/{brand}")
    public List<Car> findCarById(@PathParam("brand") String brand) {
        return carRepository.findByBrand(brand);
    }
}
