package com.assignment.moja_car_wash;

import com.assignment.moja_car_wash.domain.entities.CustomerEntity;

public class TestDataUtils {

    public static CustomerEntity createCustomerEntityForTest() {
        return CustomerEntity.builder()
                .name("john")
                .email("example@gmail.com")
                .contactNumber("0802434")
                .tag(3L)
                .build();
    }
}
