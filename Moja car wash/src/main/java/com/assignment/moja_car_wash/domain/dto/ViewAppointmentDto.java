package com.assignment.moja_car_wash.domain.dto;

import com.assignment.moja_car_wash.domain.entities.CustomerEntity;
import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAppointmentDto {
   private CustomerEntity customerEntity;
   private EmployeeEntity employeeEntity;
}
