package com.assignment.moja_car_wash.controller;

import com.assignment.moja_car_wash.domain.dto.CustomerDto;
import com.assignment.moja_car_wash.domain.dto.ViewAppointmentDto;
import com.assignment.moja_car_wash.domain.entities.CustomerEntity;
import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import com.assignment.moja_car_wash.mapper.Mapper;
import com.assignment.moja_car_wash.services.CustomerService;
import com.assignment.moja_car_wash.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CustomerController {
    private  final CustomerService customerService;
    private  final Mapper<CustomerDto, CustomerEntity> mapper;
    private final EmployeeService employeeService;

    public CustomerController(CustomerService customerService, Mapper<CustomerDto, CustomerEntity> mapper, EmployeeService employeeService) {
        this.mapper = mapper;
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/appointments")
    public ResponseEntity<CustomerDto> setAppointment(@RequestBody CustomerDto customerDto) {
        CustomerEntity customerEntity = mapper.mapFromSource(customerDto);
        CustomerEntity savedCustomer = customerService.setAppointment(customerEntity);
       return new ResponseEntity<>(mapper.mapToSource(savedCustomer), HttpStatus.CREATED);
    }

    @GetMapping(path = "/appointments/{id}")
    public ResponseEntity<ViewAppointmentDto> viewAppointment(@PathVariable(name = "id") String id) {

            CustomerEntity customerEntity = customerService.viewAppointment(id);

            if(customerEntity != null) {
                Optional<EmployeeEntity> employeeAssigned = employeeService.findById(customerEntity.getEmployeeId());
                if(employeeAssigned.isPresent()) {
                    ViewAppointmentDto viewAppointmentDto = createViewAppointmentDto(customerEntity, employeeAssigned);
                    return new ResponseEntity<>(viewAppointmentDto, HttpStatus.FOUND);
                }
                return new ResponseEntity<>(createViewAppointmentDto(customerEntity), HttpStatus.FOUND);
            }
            return ResponseEntity.notFound().build();
    }

    private ViewAppointmentDto createViewAppointmentDto(CustomerEntity customerEntity) {
        return ViewAppointmentDto.builder()
                .customerEntity(customerEntity)
                .employeeEntity(null)
                .build();
    }
    private ViewAppointmentDto createViewAppointmentDto(CustomerEntity customerEntity, Optional<EmployeeEntity> employeeAssigned) {
        return  ViewAppointmentDto.builder()
                .employeeEntity(employeeAssigned.orElse(null))
                .customerEntity(customerEntity)
                .build();
    }
}
