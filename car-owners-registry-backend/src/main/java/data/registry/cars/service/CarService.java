package data.registry.cars.service;

import data.registry.cars.model.Car;
import data.registry.cars.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    final CarRepository carRepository;

    public Page<Car> page(
            PageRequest pageRequest) {
        return carRepository.findAll(pageRequest);
    }

    public Car read(
            Integer id) {
        return carRepository.getReferenceById(id);
    }

    public Page<Car> findByValue(
            String value,
            PageRequest pageRequest) {
        return carRepository.findByMakeContainsOrModelContainsOrNumberPlateContains(value, value, value, pageRequest);
    }
}
