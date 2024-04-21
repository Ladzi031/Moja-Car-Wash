package com.assignment.moja_car_wash.repository;

import com.assignment.moja_car_wash.domain.entities.SupervisorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupervisorRepository extends CrudRepository<SupervisorEntity, String> {

    Optional<SupervisorEntity> findByUsername(String username);

}
