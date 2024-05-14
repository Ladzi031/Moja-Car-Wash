package com.assignment.moja_car_wash;

import com.assignment.moja_car_wash.domain.entities.CarEntity;

public class TestDataUtils {

    public static CarEntity createCustomerEntityForTest() {
        return CarEntity.builder()
                .name("john")
                .email("example@gmail.com")
                .contactNumber("0802434")
                .build();
    }
}
