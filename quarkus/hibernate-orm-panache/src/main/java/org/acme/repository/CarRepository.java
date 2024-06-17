package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.model.Car;

import java.util.List;

@ApplicationScoped
@Transactional
public class CarRepository implements PanacheRepository<Car> {

    public List<Car> findByBrand(String brand) {
        return list("brand", brand);
    }
}
