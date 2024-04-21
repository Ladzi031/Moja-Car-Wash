package com.assignment.moja_car_wash.controller;

import com.assignment.moja_car_wash.domain.dto.CarDto;
import com.assignment.moja_car_wash.domain.dto.UpdateRequest;
import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.mapper.Mapper;
import com.assignment.moja_car_wash.services.CarWashSupervisorService;
import com.assignment.moja_car_wash.states.CarState;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
//33d78c62-c487-4764-b6c6-6881ef4e6d0f
@RestController
@RequestMapping("/admin")
public class SuperUserController {

    private final CarWashSupervisorService supervisorService;

    private final Mapper<CarDto, CarEntity> carMapper;

    public SuperUserController(CarWashSupervisorService supervisorService, Mapper<CarDto, CarEntity> carMapper) {
        this.supervisorService = supervisorService;


        this.carMapper = carMapper;
    }

    @GetMapping(path = "/")
    public String test() {
        return "success test...";
    }

    @GetMapping(path = "/cars")
    public List<CarDto> viewAllCars() {
        List<CarEntity> cars = supervisorService.viewAllCars();
        return cars.stream().map(carMapper::mapToSource).collect(Collectors.toList());
    }

    @PatchMapping("/cars")
    public void updateCar(@RequestBody UpdateRequest updateCar) {

        if (supervisorService.existsByTag(updateCar.getCarTag())) {
            supervisorService.updateCarStates(updateCar.getCarTag(), updateCar.getIsDoneWashing());
        }
    }

    @GetMapping(path = "/cars/{state}")
    public List<CarDto> viewAllCarsByState(@PathVariable("state") String state) {
        List<CarEntity> carEntities = supervisorService.viewAllByCarStatus(CarState.valueOf(state));
        return carEntities.stream().map(carMapper::mapToSource).collect(Collectors.toList());
    }
}

//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//@PreAuthorize("hasAuthority('ROLE_user')")
