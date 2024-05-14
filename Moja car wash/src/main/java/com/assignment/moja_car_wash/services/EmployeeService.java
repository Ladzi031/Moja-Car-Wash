package com.assignment.moja_car_wash.services;

import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void scheduleEmployees(List<Long> employeeId, Boolean isWashingCar);

    Optional<EmployeeEntity> findById(Long employeeId);

    Optional<EmployeeEntity> findAvailableEmployee();

    List<EmployeeEntity> findAllAvailableEmployees();

    EmployeeEntity save(EmployeeEntity employeeEntity);

    void deleteEmployee(String employee);

    List<EmployeeEntity> findAllEmployees();
}
