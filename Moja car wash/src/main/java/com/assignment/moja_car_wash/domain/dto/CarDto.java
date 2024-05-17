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
}
// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYW1lMSIsImlhdCI6MTcxNTc3MTA0MywiZXhwIjoxNzE1Nzc0NjQzfQ.0croR23O2HINNulHA0Gd2EMMU85MzlTSbAwxNfatrzw
/*
{
   "name": "james",
    "email": "james@example.com",
    "description": "need my shit clean",
    "preferredWash": "basic clean",
    "contactNumber": "12345"
}
 */
