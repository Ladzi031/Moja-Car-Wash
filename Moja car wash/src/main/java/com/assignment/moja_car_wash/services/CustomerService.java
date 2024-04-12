package com.assignment.moja_car_wash.services;

import com.assignment.moja_car_wash.domain.entities.CustomerEntity;

import java.util.List;

public interface CustomerService {
    String BASIC_CLEAN = "basic clean";
    Double BASIC_CLEAN_PRICE = 120.0;
    String PREMIUM_CLEAN = "premium clean";
    Double PREMIUM_CLEAN_PRICE = 350.0;
    String COMPLEX_CLEAN = "complex clean";
    Double COMPLEX_CLEAN_PRICE = 420.0;


    CustomerEntity setAppointment(CustomerEntity appointment);

   CustomerEntity viewAppointment(String uniqueKey);

    List<CustomerEntity> getAllByCarStatus(String carStatus);

    void saveAll(List<CustomerEntity> cars);


}
