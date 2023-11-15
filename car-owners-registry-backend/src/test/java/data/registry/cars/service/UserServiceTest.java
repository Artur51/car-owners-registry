package data.registry.cars.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import data.registry.cars.model.User;
import data.registry.cars.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    public void testReadMethod() {
        User user = new User();
        user.setId(0);
        user.setName("name0");

        Mockito.when(userRepository.getReferenceById(Mockito.nullable(Integer.class)))
                .thenReturn(user);
        int id = 0;
        User result = userService.read(id);
        assertNotNull(result);

        Mockito.verify(userRepository, Mockito.times(1)).getReferenceById(Mockito.nullable(Integer.class));
    }

    @Test
    public void testFindByNameMethod() {
        Page<User> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(userRepository.findByNameContains(Mockito.nullable(String.class), Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        String name = "requestedName";
        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<User> result = userService.findByName(name, pageRequest);
        assertNotNull(result);

        Mockito.verify(userRepository, Mockito.times(1)).findByNameContains(Mockito.nullable(String.class), Mockito
                .nullable(PageRequest.class));
    }

    @Test
    public void testPageMethod() {
        Page<User> page = new PageImpl<>(new ArrayList<>());

        Mockito.when(userRepository.findAll(Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        PageRequest pageRequest = PageRequest.of(0, 20);
        Page<User> result = userService.page(pageRequest);
        assertNotNull(result);

        Mockito.verify(userRepository, Mockito.times(1)).findAll(Mockito.nullable(PageRequest.class));
    }

}
