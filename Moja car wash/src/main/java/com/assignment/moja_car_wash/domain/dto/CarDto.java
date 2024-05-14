package com.assignment.moja_car_wash.domain.dto;

import com.assignment.moja_car_wash.states.CarState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarDto {
    private String uuid;
    private String name;
    private String email;
    private String description;
    private String preferredWash;
    private String contactNumber;
    private String carState = CarState.PRE_WASH.toString();
    //{uuid: '6737e041-26ad-4c1f-996a-c305cab00e90
}
/*
{
   "name": "james",
    "email": "james@example.com",
    "description": "need my shit clean",
    "preferredWash": "basic clean",
    "contactNumber": "12345"
}

* */
