package com.assignment.moja_car_wash.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDto {
    private String uuid;
    private String name;
    private String email;
    private String time;
    private String description;
    private String preferredWash;
    private String contactNumber;
}

