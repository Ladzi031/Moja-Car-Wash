package com.assignment.moja_car_wash.services.impl;

import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.repository.CarRepository;
import com.assignment.moja_car_wash.services.CarWashSupervisorService;
import com.assignment.moja_car_wash.states.CarState;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SupervisorServiceImpl implements CarWashSupervisorService {

    private final CarRepository carRepository;

    public SupervisorServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void updateCarStates(String carTag, Boolean isDoneWashing) {

        // the supervisor gets to sign off a car, after inspecting, if the car is clean or not (has to meet certain standards...)

        Optional<CarEntity> car = carRepository.findById(carTag);
        if (car.isPresent()) {
            CarEntity customerCar = car.get();
            customerCar.setIsDoneWashing(isDoneWashing);
            carRepository.save(customerCar);
        }
    }

    @Override
    public List<CarEntity> viewAllCars() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarEntity> viewAllByCarStatus(CarState carState) {
        return carRepository.findAllByCarState(carState.toString());
    }

    @Override
    public boolean existsByTag(String carTag) {
        return carRepository.existsById(carTag);
    }


}
