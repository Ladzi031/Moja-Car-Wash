package com.assignment.moja_car_wash.domain.entities;

import com.assignment.moja_car_wash.services.CarStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    private String uuid;
    private String name;
    private String email;
    private String time;
    private String contactNumber;
    private String description;
    private String preferredWash;
    private String carStatus = CarStatus.PRE_WASH.toString();
    private Double price = null;
    private Long employeeId = null;
    private Boolean isDoneWashing;
}
