package com.assignment.moja_car_wash.services.impl;

import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import com.assignment.moja_car_wash.repository.EmployeeRepository;
import com.assignment.moja_car_wash.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Log
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

     @PostConstruct
    public void populateRepository() {
        List<EmployeeEntity> employeeEntity = createFakeEmployees();
        employeeRepository.saveAll(employeeEntity);
    }

    private List<EmployeeEntity> createFakeEmployees() {
        List<EmployeeEntity> list = new ArrayList<>();
        EmployeeEntity em = EmployeeEntity.builder()
                .name("Sibongiseni")
                .WashingCar(false)
                .build();
        list.add(em);
        EmployeeEntity em2 = EmployeeEntity.builder()
                .name("Itumeleng")
                .WashingCar(false)
                .build();
        list.add(em2);
        EmployeeEntity em3 = EmployeeEntity.builder()
                .name("Ofentse")
                .WashingCar(false)
                .build();
        list.add(em3);
        EmployeeEntity em4 = EmployeeEntity.builder()
                .name("Thabo")
                .WashingCar(false)
                .build();
        list.add(em4);
        return list;
    }

    @Override
    public void scheduleEmployees(List<Long> employeeIdList, Boolean isWashingCar) {
        List<EmployeeEntity> empToUp = new ArrayList<>();

        if (!employeeIdList.isEmpty()) {
            StreamSupport.stream(employeeRepository.findAllById(employeeIdList).spliterator(), false)
                    .forEach(empToUp::add);
            empToUp.forEach(e -> e.setWashingCar(isWashingCar));
            employeeRepository.saveAll(empToUp);
            employeeIdList.clear();
        }
    }

    @Override
    public Optional<EmployeeEntity> findById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Optional<EmployeeEntity> findAvailableEmployee() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .filter(e -> e.getWashingCar() == false)
                .findFirst();
    }

    @Override
    public List<EmployeeEntity> findAllAvailableEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .filter(e -> e.getWashingCar() == false)
                .toList();
    }

    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public void deleteEmployee(String employee_id) {

        employeeRepository.deleteById(Long.valueOf(employee_id));
    }

    @Override
    public List<EmployeeEntity> findAllEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
