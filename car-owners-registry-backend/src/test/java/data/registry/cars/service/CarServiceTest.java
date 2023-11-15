package data.registry.cars.service;

import data.registry.cars.model.Car;
import data.registry.cars.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @InjectMocks
    CarService carService;
    @Mock
    CarRepository carRepository;

    @Test
    public void testReadMethod() {
        Car car = new Car();
        Mockito.when(carRepository.getReferenceById(Mockito.nullable(Integer.class))).thenReturn(car);

        Integer id = 0;
        Car result = carService.read(id);
        assertNotNull(result);
        Mockito.verify(carRepository, Mockito.times(1)).getReferenceById(Mockito.nullable(Integer.class));
    }

    @Test
    public void testPageMethod() {
        Page<Car> page = new PageImpl<>(new ArrayList<>());

        Mockito.when(carRepository.findAll(Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Car> result = carService.page(pageRequest);
        assertNotNull(result);

        Mockito.verify(carRepository, Mockito.times(1)).findAll(Mockito.nullable(PageRequest.class));
    }

    @Test
    public void testFindByValueMethod() {
        Page<Car> page = new PageImpl<>(new ArrayList<>());

        Mockito.when(carRepository.findByMakeContainsOrModelContainsOrNumberPlateContains(Mockito
                        .nullable(String.class), Mockito.nullable(String.class), Mockito.nullable(String.class), Mockito
                        .nullable(PageRequest.class)))
                .thenReturn(page);

        String value = "value";
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<Car> result = carService.findByValue(value, pageRequest);
        assertNotNull(result);

        Mockito.verify(carRepository, Mockito.times(1)).findByMakeContainsOrModelContainsOrNumberPlateContains(Mockito
                .nullable(String.class), Mockito.nullable(String.class), Mockito.nullable(String.class), Mockito
                .nullable(PageRequest.class));
    }

}
