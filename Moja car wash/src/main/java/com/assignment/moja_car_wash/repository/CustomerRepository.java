package com.assignment.moja_car_wash.repository;

import com.assignment.moja_car_wash.domain.entities.CustomerEntity;
import com.assignment.moja_car_wash.services.CarStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

    List<CustomerEntity> findAllByCarStatus(String carStatus);

}
