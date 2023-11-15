package data.registry.cars.controller;

import data.registry.cars.error_handling.ErrorResponseExceptionHandler;
import data.registry.cars.model.Car;
import data.registry.cars.model.User;
import data.registry.cars.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(
        classes = {
                UserService.class,
                UserController.class,
                ErrorResponseExceptionHandler.class
        })
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    @SneakyThrows
    public void testReadMethod_Endpoint_Should_SuccessfulReturnResult() {
        User user = new User();
        user.setId(1);
        user.setName("name0");

        Mockito.when(userService.read(Mockito.anyInt()))
                .thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("name").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("name0"));

        Mockito.verify(userService, Mockito.times(1)).read(Mockito.anyInt());
    }

    @Test
    @SneakyThrows
    public void testPageMethod_Endpoint_Should_SuccessfulReturnResult() {
        Page<User> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(userService.page(Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "0");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/users").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        ArgumentCaptor<PageRequest> args = ArgumentCaptor.forClass(PageRequest.class);
        Mockito.verify(userService, Mockito.times(1)).page(args.capture());

        Sort expectedSort = Sort.unsorted();
        assertEquals(expectedSort, args.getValue().getSort());
    }

    @Test
    @SneakyThrows
    public void testFindByNameMethod_Endpoint_Should_SuccessfulReturnResult_When_Find_And_Sort() {
        Page<User> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(userService.findByName(Mockito.nullable(String.class), Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("find", "value");
        requestParams.add("sort", "name:desc");
        requestParams.add("page", "0");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/users").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        ArgumentCaptor<PageRequest> args = ArgumentCaptor.forClass(PageRequest.class);

        Mockito.verify(userService, Mockito.times(1)).findByName(Mockito.nullable(String.class), args.capture());

        Sort expectedSort = Sort.by("name").descending();
        assertEquals(expectedSort, args.getValue().getSort());
    }

    @Test
    @SneakyThrows
    public void testFindByNameMethod_Endpoint_Should_SuccessfulReturnResult_When_Sort_NoFind() {
        Page<User> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(userService.page(Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("sort", "name:desc");
        requestParams.add("page", "0");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/users").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        ArgumentCaptor<PageRequest> args = ArgumentCaptor.forClass(PageRequest.class);

        Mockito.verify(userService, Mockito.times(1)).page(args.capture());

        Sort expectedSort = Sort.by("name").descending();
        assertEquals(expectedSort, args.getValue().getSort());
    }

    @Test
    @SneakyThrows
    public void testReadCarsMethod_Endpoint_Should_SuccessfulReturnResult() {
        User user = new User();
        user.setId(2);
        user.setName("name0");
        List<Car> cars = new ArrayList<>();

        Car car = new Car();
        car.setId(19);
        car.setMake("make1");
        car.setModel("model1");
        car.setNumberPlate("numberPlate1");

        cars.add(car);
        user.setCars(cars);

        Mockito.when(userService.read(Mockito.anyInt()))
                .thenReturn(user);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/users/2/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        String content = result.andReturn().getResponse().getContentAsString();
        String expected = "[{\"id\":19,\"make\":\"make1\",\"model\":\"model1\",\"numberplate\":\"numberPlate1\"}]";
        assertEquals(expected, content);

        Mockito.verify(userService, Mockito.times(1)).read(Mockito.anyInt());
    }

    @Test
    @SneakyThrows
    public void testFindByNameMethod_Endpoint_Should_ReturnBadRequest_Due_ThrownException_When_SortIsInvalid() {
        Page<User> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(userService.findByName(Mockito.nullable(String.class), Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("find", "value");
        requestParams.add("sort", "value");
        requestParams.add("page", "0");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/users").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(userService, Mockito.times(0)).findByName(Mockito.nullable(String.class), Mockito
                .nullable(PageRequest.class));
    }

    @Test
    @SneakyThrows
    public void testReadCarsMethod_Endpoint_Should_ReturnNotFound_Due_EntityNotFoundException() {

        Mockito.when(userService.read(Mockito.anyInt())).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/2/cars"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        Mockito.verify(userService, Mockito.times(1)).read(Mockito.anyInt());
    }

    @Test
    @SneakyThrows
    public void testFindByNameMethod_Endpoint_Should_ReturnBadRequest_Due_ThrownException_When_PageParameterIsInvalid() {
        Page<User> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(userService.findByName(Mockito.nullable(String.class), Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "invalidValue");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/users").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(userService, Mockito.times(0)).findByName(Mockito.nullable(String.class), Mockito
                .nullable(PageRequest.class));
    }

    @Test
    @SneakyThrows
    public void testFindByNameMethod_Endpoint_Should_ReturnBadRequest_Due_ThrownException_When_SortByPropertyNotFound() {
        Mockito.when(userService.page(Mockito.nullable(PageRequest.class)))
                .thenThrow(Mockito.mock(PropertyReferenceException.class));

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("sort", "notExistedName:asc");

        mockMvc.perform(MockMvcRequestBuilders.get("/users").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(userService, Mockito.times(1)).page(Mockito
                .nullable(PageRequest.class));
    }
}
