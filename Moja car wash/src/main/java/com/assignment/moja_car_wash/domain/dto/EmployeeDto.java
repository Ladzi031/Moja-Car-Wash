package com.assignment.moja_car_wash.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {
    private Long employee_id = null;
    private String name;
    private Boolean washingCar = false;
}
