package com.assignment.moja_car_wash.services;

import com.assignment.moja_car_wash.domain.entities.CarEntity;

import java.util.List;

/**
 *  A simple no-reply email notification service to inform user about the status of the car
 */
public interface EmailService {
    public Boolean verifyEmail(String email);
    public void notify(CarEntity carOwner);

}
