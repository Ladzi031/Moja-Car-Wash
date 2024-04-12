package com.assignment.moja_car_wash.services;

import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void delegateEmployees(List<Long> employeeId);


    Optional<EmployeeEntity> findById(Long employeeId);

    Optional<EmployeeEntity> findAvailableEmployee();

    List<EmployeeEntity> findAllAvailableEmployees();

    void save(EmployeeEntity employeeEntity);
}
