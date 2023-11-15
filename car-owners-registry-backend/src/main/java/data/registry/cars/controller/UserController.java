package data.registry.cars.controller;

import data.registry.cars.model.Car;
import data.registry.cars.model.User;
import data.registry.cars.service.UserService;
import data.registry.cars.utils.Utils;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Tag(
        name = "UserController",
        description = "User registry data endpoint. It allows finding user data or retrieving user or user car data by id.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Correct response with user data."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid.")
            })
    @GetMapping
    public Page<User> findByName(
            @RequestParam(name = "find", defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) String sort,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "20", required = false) int size) {
        Sort sortValue = sort.isEmpty() ? Sort.unsorted() : Utils.parseSort(sort);
        if (!name.isEmpty()) {
            return userService.findByName(name, PageRequest.of(page, size, sortValue));
        }
        return userService.page(PageRequest.of(page, size, sortValue));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Correct response with user data."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User data for given id is not found.")
            })
    @GetMapping("/{id}")
    public User read(
            @PathVariable(name = "id") int id) {
        return userService.read(id);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Correct response with user cars data."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User data for given id is not found.")
            })
    @GetMapping("/{id}/cars")
    public List<Car> readCars(
            @PathVariable(name = "id") int id) {
        User user = userService.read(id);
        return user == null ? Collections.emptyList() : user.getCars();
    }
}
