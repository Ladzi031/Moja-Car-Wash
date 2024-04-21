package com.assignment.moja_car_wash.services.impl;

import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.repository.CarRepository;
import com.assignment.moja_car_wash.services.CustomerService;
import com.assignment.moja_car_wash.services.EmployeeService;
import com.assignment.moja_car_wash.states.CarState;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Service
@Log
public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;
    private final EmployeeService employeeService;

    public CustomerServiceImpl(CarRepository carRepository, EmployeeService employeeService) {
        this.carRepository = carRepository;
        this.employeeService = employeeService;
    }

    @Override
    public CarEntity setAppointment(CarEntity appointment) {
        String uuid = UUID.randomUUID().toString();
        appointment.setUuid(uuid);
        determineAppointmentPrice(appointment);
        assignEmployeeToCustomer(appointment);

        return carRepository.save(appointment);
    }

    @Override
    public CarEntity viewAppointment(String uniqueKey) {
        Optional<CarEntity> customerInfo = carRepository.findById(uniqueKey);
        return customerInfo.orElse(null);
    }

    private void assignEmployeeToCustomer(CarEntity appointment) {
        employeeService.findAvailableEmployee().ifPresentOrElse(e -> {
                    appointment.setEmployeeId(e.getEmployee_id());
                    appointment.setCarState(CarState.WASHING.toString());
                    e.setWashingCar(true);
                    employeeService.save(e);
                },
                () -> appointment.setEmployeeId(null));
    }

    private void determineAppointmentPrice(CarEntity appointment) {
        switch (appointment.getPreferredWash()) {
            case BASIC_CLEAN -> appointment.setPrice(BASIC_CLEAN_PRICE);
            case PREMIUM_CLEAN -> appointment.setPrice(PREMIUM_CLEAN_PRICE);
            case COMPLEX_CLEAN -> appointment.setPrice(COMPLEX_CLEAN_PRICE);
            default -> {
                log.log(Level.INFO, "defaulting price to basic clean");
                appointment.setCarState(BASIC_CLEAN);
                appointment.setPrice(BASIC_CLEAN_PRICE);
            }
        }
    }

    @Override
    public List<CarEntity> findAllByCarState(String carStatus) {
        return carRepository.findAllByCarState(carStatus);
    }

    @Override
    public void saveAll(List<CarEntity> newCarsStates) {
        carRepository.saveAll(newCarsStates);
    }


}
