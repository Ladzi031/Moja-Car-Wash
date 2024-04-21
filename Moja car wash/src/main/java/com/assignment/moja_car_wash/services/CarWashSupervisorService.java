package com.assignment.moja_car_wash.services;

import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.states.CarState;

import java.util.List;

public interface CarWashSupervisorService {
    void updateCarStates(String carTag, Boolean isDoneWashing);

    List<CarEntity> viewAllCars();

    List<CarEntity> viewAllByCarStatus(CarState carState);


    boolean existsByTag(String carTag);
}
