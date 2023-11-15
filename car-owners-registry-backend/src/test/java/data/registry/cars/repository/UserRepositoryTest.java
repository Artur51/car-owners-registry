package data.registry.cars.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import data.registry.cars.model.User;

@DataJpaTest
@Sql({ "/test-data.sql" })
@TestPropertySource("file:./src/test/resources/application-test.yaml")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByNameMethod() {

        List<User> allUsers = userRepository.findAll();
        assertNotNull(allUsers);
        assertEquals(5, allUsers.size());

        String name = "u";
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<User> result = userRepository.findByNameContains(name, pageRequest);
        assertNotNull(result);
        List<User> content = result.getContent();
        assertNotNull(content);
        assertEquals(3, content.size());
    }

}
