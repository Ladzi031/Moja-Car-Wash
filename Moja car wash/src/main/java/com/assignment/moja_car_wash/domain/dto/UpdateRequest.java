package com.assignment.moja_car_wash.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateRequest {
    private String carTag;
    private Boolean isDoneWashing;
}
