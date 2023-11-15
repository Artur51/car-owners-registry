package data.registry.cars.repository;

import data.registry.cars.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Page<Car> findByMakeContainsOrModelContainsOrNumberPlateContains(
            String make,
            String model,
            String numberPlate,
            PageRequest pageRequest);

}
