package com.assignment.moja_car_wash;

import com.assignment.moja_car_wash.domain.entities.CustomerEntity;

public class TestDataUtil {
    public static CustomerEntity createCustomerTest() {
        return CustomerEntity.builder().name("john").description("i want my car squeaky").preferredWash("basic clean").build();
    }
}
