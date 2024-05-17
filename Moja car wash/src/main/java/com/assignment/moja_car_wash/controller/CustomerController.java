package com.assignment.moja_car_wash.controller;

import com.assignment.moja_car_wash.domain.dto.CarDto;
import com.assignment.moja_car_wash.domain.dto.ViewAppointmentDto;
import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import com.assignment.moja_car_wash.mapper.Mapper;
import com.assignment.moja_car_wash.services.CustomerService;
import com.assignment.moja_car_wash.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final Mapper<CarDto, CarEntity> mapper;
    private final EmployeeService employeeService;

    public CustomerController(CustomerService customerService, Mapper<CarDto, CarEntity> mapper, EmployeeService employeeService) {
        this.mapper = mapper;
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String test() {
        return "hello world";
    }

    @PostMapping(path = "/appointments")
    public ResponseEntity<CarDto> setAppointment(@RequestBody CarDto carDto) {
        CarEntity carEntity = mapper.mapFromSource(carDto);
        CarEntity savedCustomer = customerService.setAppointment(carEntity);
        return new ResponseEntity<>(mapper.mapToSource(savedCustomer), HttpStatus.CREATED);
    }

    @GetMapping(path = "/appointments/{id}")
    public ResponseEntity<ViewAppointmentDto> viewAppointment(@PathVariable(name = "id") String id) {

        CarEntity carEntity = customerService.viewAppointment(id);

        if (carEntity != null) {
            if(carEntity.getEmployeeId() != null) {
                Optional<EmployeeEntity> employeeAssigned = employeeService.findById(carEntity.getEmployeeId());
                if (employeeAssigned.isPresent()) {
                    ViewAppointmentDto viewAppointmentDto = createViewAppointmentDto(carEntity, employeeAssigned);
                    return new ResponseEntity<>(viewAppointmentDto, HttpStatus.FOUND);
                }
            }
            return new ResponseEntity<>(createViewAppointmentDto(carEntity), HttpStatus.FOUND);
        }
        return ResponseEntity.notFound().build();
    }

    private ViewAppointmentDto createViewAppointmentDto(CarEntity carEntity) {
        return ViewAppointmentDto.builder()
                .carEntity(carEntity)
                .employeeEntity(null)
                .build();
    }

    private ViewAppointmentDto createViewAppointmentDto(CarEntity carEntity, Optional<EmployeeEntity> employeeAssigned) {
        return ViewAppointmentDto.builder()
                .employeeEntity(employeeAssigned.orElse(null))
                .carEntity(carEntity)
                .build();
    }
}
