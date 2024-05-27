package com.assignment.moja_car_wash.services.impl;

import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import com.assignment.moja_car_wash.services.BusinessOperations;
import com.assignment.moja_car_wash.services.CustomerService;
import com.assignment.moja_car_wash.services.EmailService;
import com.assignment.moja_car_wash.services.EmployeeService;
import com.assignment.moja_car_wash.states.CarState;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NormalBusinessOperationsImpl implements BusinessOperations {
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    private final List<Long> employeeIdList = new ArrayList<>();
    private final List<CarEntity> cars = new ArrayList<>();

    public NormalBusinessOperationsImpl(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
        }

    // 600000L --> every ten minutes

    @Scheduled(fixedRate = 5000L)
    public void taskScheduler() {

        List<CarEntity> customerCars = customerService.findAllByCarState(CarState.WASHING.toString());

        customerCars.stream()
                .parallel()
                .filter(CarEntity::getIsDoneWashing)
                .forEach(c -> {
                    c.setCarState(CarState.COMPLETED.toString());
                    cars.add(c);
                    employeeIdList.add(c.getEmployeeId());
                });

        employeeService.scheduleEmployees(employeeIdList, false);
        customerService.saveAll(cars);

        employeeIdList.clear();
        cars.clear();

        List<CarEntity> preWashedCars = customerService.findAllByCarState(CarState.PRE_WASH.toString());
        List<EmployeeEntity> availableEmployees = employeeService.findAllAvailableEmployees();

        issueTasks(availableEmployees, preWashedCars);
    }

    @Override
    public void issueTasks(List<EmployeeEntity> availableEmployees, List<CarEntity> preWashedCars) {
        if (!preWashedCars.isEmpty() && !availableEmployees.isEmpty()) {

            List<Long> employeeIdList = new ArrayList<>();
            List<CarEntity> cars = new ArrayList<>();

            int size = Math.min(availableEmployees.size(), preWashedCars.size());

            for (int i = 0; i < size; i++) {

                CarEntity car = preWashedCars.get(i);
                EmployeeEntity employee = availableEmployees.get(i);

                car.setEmployeeId(employee.getEmployee_id());
                car.setCarState(CarState.WASHING.toString());
                employee.setWashingCar(true);
                cars.add(car);
                employeeIdList.add(employee.getEmployee_id());
            }

            employeeService.scheduleEmployees(employeeIdList, true);
            customerService.saveAll(cars);
            employeeIdList.clear();
            cars.clear();
        }
    }

}
