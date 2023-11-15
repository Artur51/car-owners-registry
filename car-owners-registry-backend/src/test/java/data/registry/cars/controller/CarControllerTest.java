package data.registry.cars.controller;

import data.registry.cars.model.Car;
import data.registry.cars.service.CarService;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(
        classes = {
                CarService.class,
                CarController.class
        })
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CarService carService;

    @Test
    @SneakyThrows
    public void testSortValueMethod_Endpoint_Should_SuccessfulReturnResult() {
        Page<Car> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(carService.page(Mockito.any(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("page", "0");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/cars").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(carService, Mockito.times(1)).page(Mockito.nullable(PageRequest.class));
    }

    @Test
    @SneakyThrows
    public void testSortValueMethod_Endpoint_Should_SuccessfulReturnResult_When_NoParams() {
        Page<Car> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(carService.page(Mockito.any(PageRequest.class)))
                .thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        ArgumentCaptor<PageRequest> args = ArgumentCaptor.forClass(PageRequest.class);
        Mockito.verify(carService, Mockito.times(1)).page(args.capture());

        Sort expectedSort = Sort.unsorted();
        assertEquals(expectedSort, args.getValue().getSort());
    }

    @Test
    @SneakyThrows
    public void testFindByValueMethod_Endpoint_Should_SuccessfulReturnResult_When_Find_And_Sort() {
        Page<Car> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(carService.findByValue(Mockito.anyString(), Mockito.any(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("find", "value");
        requestParams.add("sort", "name:asc");
        requestParams.add("page", "0");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/cars").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        ArgumentCaptor<PageRequest> args = ArgumentCaptor.forClass(PageRequest.class);
        Mockito.verify(carService, Mockito.times(1)).findByValue(Mockito.nullable(String.class), args.capture());

        Sort expectedSort = Sort.by("name").ascending();
        assertEquals(expectedSort, args.getValue().getSort());
    }

    @Test
    @SneakyThrows
    public void testFindByValueMethod_Endpoint_Should_SuccessfulReturnResult_When_Sort_NoFind() {
        Page<Car> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(carService.page(Mockito.nullable(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("sort", "name:desc");
        requestParams.add("page", "0");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/cars").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        ArgumentCaptor<PageRequest> args = ArgumentCaptor.forClass(PageRequest.class);
        Mockito.verify(carService, Mockito.times(1)).page(args.capture());

        Sort expectedSort = Sort.by("name").descending();
        assertEquals(expectedSort, args.getValue().getSort());
    }

    @Test
    @SneakyThrows
    public void testReadMethod_Endpoint_Should_SuccessfulReturnResult() {
        Car car = new Car();
        car.setId(2);
        car.setMake("make0");
        car.setModel("model0");
        car.setNumberPlate("numberPlate1");

        Mockito.when(carService.read(Mockito.nullable(Integer.class)))
                .thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("model").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("model").value("model0"))
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("make").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("make").value("make0"))
                .andExpect(MockMvcResultMatchers.jsonPath("numberplate").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("numberplate").value("numberPlate1"));

        Mockito.verify(carService, Mockito.times(1)).read(Mockito.nullable(Integer.class));
    }

    @Test
    @SneakyThrows
    public void testFindByValueMethod_Endpoint_Should_ReturnBadRequest_Due_ThrownException_InCaseSortIsInvalid() {
        Page<Car> page = new PageImpl<>(new ArrayList<>());
        Mockito.when(carService.findByValue(Mockito.anyString(), Mockito.any(PageRequest.class)))
                .thenReturn(page);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("find", "value");
        requestParams.add("sort", "name");
        requestParams.add("page", "0");
        requestParams.add("size", "20");

        mockMvc.perform(MockMvcRequestBuilders.get("/cars").params(requestParams))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(carService, Mockito.times(0)).findByValue(Mockito.nullable(String.class), Mockito
                .nullable(PageRequest.class));
    }

}
