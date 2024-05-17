package com.assignment.moja_car_wash.controller;

import com.assignment.moja_car_wash.domain.dto.CarDto;
import com.assignment.moja_car_wash.domain.dto.EmployeeDto;
import com.assignment.moja_car_wash.domain.dto.UpdateRequest;
import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import com.assignment.moja_car_wash.mapper.Mapper;
import com.assignment.moja_car_wash.services.CarWashSupervisorService;
import com.assignment.moja_car_wash.states.CarState;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class SuperUserController {

    private final CarWashSupervisorService supervisorService;

    private final Mapper<CarDto, CarEntity> carMapper;

    private final Mapper<EmployeeDto, EmployeeEntity> employeeMapper;

    public SuperUserController(CarWashSupervisorService supervisorService, Mapper<CarDto, CarEntity> carMapper, Mapper<EmployeeDto, EmployeeEntity> employeeMapper) {
        this.supervisorService = supervisorService;
        this.carMapper = carMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping(path = "/")
    public String test() {
        return "yep, endpoint hit... :)";
    }

    @GetMapping(path = "/cars")
    public List<CarDto> viewAllCars() {
        List<CarEntity> cars = supervisorService.viewAllCars();
        return cars.stream()
                .map(carMapper::mapToSource)
                .collect(Collectors.toList());
    }

    @PatchMapping("/cars")
    public ResponseEntity updateCar(@RequestBody UpdateRequest updateCar) {

        supervisorService.updateCarStates(updateCar.getCarTag(), updateCar.getIsDoneWashing());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/cars/{state}")
    public List<CarDto> viewAllCarsByState(@PathVariable("state") String state) {
        List<CarEntity> carEntities = supervisorService.viewAllByCarStatus(CarState.valueOf(state));
        return carEntities.stream()
                .map(carMapper::mapToSource)
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/employees")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {

        EmployeeEntity employeeEntity = employeeMapper.mapFromSource(employeeDto);
        EmployeeEntity savedEmployee = supervisorService.addEmployee(employeeEntity);
        return new ResponseEntity<>(employeeMapper.mapToSource(savedEmployee), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/employees/{id}")
    public ResponseEntity deleteEmployee(@PathVariable(name = "id") String employee_id) {
        supervisorService.deleteEmployee(employee_id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/employees")
    public List<EmployeeDto> viewAllEmployees() {
        List<EmployeeEntity> employeeEntities = supervisorService.viewAllEmployees();
        return employeeEntities.stream().map(employeeMapper::mapToSource).collect(Collectors.toList());
    }

}

//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//@PreAuthorize("hasAuthority('ROLE_user')")
