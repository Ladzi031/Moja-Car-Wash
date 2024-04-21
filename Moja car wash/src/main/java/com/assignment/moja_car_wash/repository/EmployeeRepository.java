package com.assignment.moja_car_wash.repository;

import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {


}
