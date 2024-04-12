package com.assignment.moja_car_wash.services.impl;

import com.assignment.moja_car_wash.domain.entities.CustomerEntity;
import com.assignment.moja_car_wash.repository.CustomerRepository;
import com.assignment.moja_car_wash.services.CarStatus;
import com.assignment.moja_car_wash.services.CustomerService;
import com.assignment.moja_car_wash.services.EmployeeService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Service
@Log
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeService employeeService;

    public CustomerServiceImpl(CustomerRepository customerRepository,  EmployeeService employeeService) {
        this.customerRepository = customerRepository;
        this.employeeService = employeeService;
    }

    @Override
    public CustomerEntity setAppointment(CustomerEntity appointment) {
        String uuid = UUID.randomUUID().toString();
        appointment.setUuid(uuid);
        determineAppointmentPrice(appointment);
        assignEmployeeToCustomer(appointment);

        return customerRepository.save(appointment);
    }

    @Override
    public CustomerEntity viewAppointment(String uniqueKey) {
        Optional<CustomerEntity> customerInfo = customerRepository.findById(uniqueKey);
        return customerInfo.orElse(null);
    }

    private void assignEmployeeToCustomer(CustomerEntity appointment) {
        employeeService.findAvailableEmployee().ifPresentOrElse(e -> {
            appointment.setEmployeeId(e.getEmployee_id());
            appointment.setCarStatus(CarStatus.WASH.toString());
            e.setWashingCar(true);
            employeeService.save(e);
            },
                () -> appointment.setEmployeeId(null));
    }

    private void determineAppointmentPrice(CustomerEntity appointment) {
        switch (appointment.getPreferredWash()) {
            case BASIC_CLEAN -> appointment.setPrice(BASIC_CLEAN_PRICE);
            case PREMIUM_CLEAN -> appointment.setPrice(PREMIUM_CLEAN_PRICE);
            case COMPLEX_CLEAN -> appointment.setPrice(COMPLEX_CLEAN_PRICE);
            default -> {
                log.log(Level.INFO, "defaulting price to basic clean");
                appointment.setCarStatus(BASIC_CLEAN);
                appointment.setPrice(BASIC_CLEAN_PRICE);
            }
        }
    }
    @Override
    public List<CustomerEntity> getAllByCarStatus(String carStatus) {
        return customerRepository.findAllByCarStatus(carStatus);
    }

    @Override
    public void saveAll(List<CustomerEntity> newCarsStates) {
        customerRepository.saveAll(newCarsStates);
    }

}
