package com.assignment.moja_car_wash.services.impl;

import com.assignment.moja_car_wash.domain.entities.CustomerEntity;
import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import com.assignment.moja_car_wash.services.BusinessOperations;
import com.assignment.moja_car_wash.services.CarStatus;
import com.assignment.moja_car_wash.services.CustomerService;
import com.assignment.moja_car_wash.services.EmployeeService;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class NormalBusinessOperationsImpl implements BusinessOperations {

    boolean anHourLapsed = true;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    public NormalBusinessOperationsImpl(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @Scheduled(fixedRate = 600000L) //5000L , every ten minutes
    public void taskScheduler() {

        anHourLapsed = !anHourLapsed;
        List<Long> employeeIdList = new ArrayList<>();
        List<CustomerEntity> cars = new ArrayList<>();
        List<CustomerEntity> customerCars = customerService.getAllByCarStatus(CarStatus.WASH.toString());

        customerCars.stream()
                .filter(CustomerEntity::getIsDoneWashing)
                .forEach(c -> {
                    c.setCarStatus(CarStatus.COMPLETED.toString());
                    cars.add(c);
                    employeeIdList.add(c.getEmployeeId());
                });

        employeeService.delegateEmployees(employeeIdList);
        customerService.saveAll(cars);

        employeeIdList.clear();
        cars.clear();

        List<CustomerEntity> preWashedCars = customerService.getAllByCarStatus(CarStatus.PRE_WASH.toString());
        List<EmployeeEntity> availableEmployees = employeeService.findAllAvailableEmployees();

        issueTasks(availableEmployees, preWashedCars);
    }

    @Override
    public void issueTasks(List<EmployeeEntity> availableEmployees, List<CustomerEntity> preWashedCars) {
         if(!preWashedCars.isEmpty() && !availableEmployees.isEmpty()) {

            List<Long> employeeIdList = new ArrayList<>();
             List<CustomerEntity> cars = new ArrayList<>();

            int size = Math.min(availableEmployees.size(), preWashedCars.size());

            for(int i = 0; i < size; i++) {

                CustomerEntity car = preWashedCars.get(i);
                EmployeeEntity employee = availableEmployees.get(i);

                car.setEmployeeId(employee.getEmployee_id());
                car.setCarStatus(CarStatus.WASH.toString());
                employee.setWashingCar(true);
                cars.add(car);
                employeeIdList.add(employee.getEmployee_id());
            }

            employeeService.delegateEmployees(employeeIdList);
            customerService.saveAll(cars);
            employeeIdList.clear();
            cars.clear();
        }

    }

}
