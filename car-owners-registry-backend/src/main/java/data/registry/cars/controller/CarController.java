package data.registry.cars.controller;

import data.registry.cars.model.Car;
import data.registry.cars.service.CarService;
import data.registry.cars.utils.Utils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CarController",
        description = "Cars information endpoint. It allows finding or retrieving car data by id or given text to search.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    final CarService carService;

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Correct response with car data."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid.")
            })
    @GetMapping
    public Page<Car> findByValue(
            @RequestParam(name = "find", defaultValue = "", required = false) String value,
            @RequestParam(defaultValue = "", required = false) String sort,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size) {
        Sort sortValue = sort.isEmpty() ? Sort.unsorted() : Utils.parseSort(sort);
        if (!value.isEmpty()) {
            return carService.findByValue(value, PageRequest.of(page, size, sortValue));
        }
        return carService.page(PageRequest.of(page, size, sortValue));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Correct response with car data."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User data for given id is not found.")
            })
    @GetMapping("/{id}")
    public Car read(
            @PathVariable(name = "id") Integer id) {
        return carService.read(id);
    }
}
