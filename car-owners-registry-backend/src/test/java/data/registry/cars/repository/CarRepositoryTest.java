package data.registry.cars.repository;

import data.registry.cars.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql({"/test-data.sql"})
@TestPropertySource("file:./src/test/resources/application-test.yaml")
public class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;

    @Test
    public void testFindByMakeContainsOrModelContainsOrNumberPlateContainsMethod() {
        String value = "6";
        PageRequest pageRequest = PageRequest.of(0, 20);

        List<Car> findAll = carRepository.findAll();
        assertNotNull(findAll);
        assertEquals(9, findAll.size());

        Page<Car> result = carRepository
                .findByMakeContainsOrModelContainsOrNumberPlateContains(value, value, value, pageRequest);
        assertNotNull(result);
        List<Car> content = result.getContent();

        assertNotNull(content);
        assertEquals(3, content.size());

        Car car = new Car();
        car.setId(6);
        car.setMake("Audi");
        car.setModel("A6");
        car.setNumberPlate("997HHH");
        assertTrue(content.contains(car));

        car = new Car();
        car.setId(7);
        car.setMake("BMW");
        car.setModel("760");
        car.setNumberPlate("444RRR");
        assertTrue(content.contains(car));

        car = new Car();
        car.setId(8);
        car.setMake("Audi");
        car.setModel("A6");
        car.setNumberPlate("876OUI");
        assertTrue(content.contains(car));
    }

}
