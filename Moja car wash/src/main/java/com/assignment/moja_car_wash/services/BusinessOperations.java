package com.assignment.moja_car_wash.services;

import com.assignment.moja_car_wash.domain.entities.CustomerEntity;
import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;

import java.util.List;

public interface BusinessOperations {
    void issueTasks(List<EmployeeEntity> availableEmployees, List<CustomerEntity> preWashedCars);
}
