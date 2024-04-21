package com.assignment.moja_car_wash.repository;

import com.assignment.moja_car_wash.domain.entities.CarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, String> {

    List<CarEntity> findAllByCarState(String carStatus);

}
