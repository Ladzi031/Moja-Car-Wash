package com.assignment.moja_car_wash.mapper.impl;

import com.assignment.moja_car_wash.domain.dto.EmployeeDto;
import com.assignment.moja_car_wash.domain.entities.EmployeeEntity;
import com.assignment.moja_car_wash.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapperImpl implements Mapper<EmployeeDto, EmployeeEntity> {
    private final ModelMapper mapper;


    public EmployeeMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public EmployeeEntity mapFromSource(EmployeeDto s) {
        return mapper.map(s, EmployeeEntity.class);
    }

    @Override
    public EmployeeDto mapToSource(EmployeeEntity d) {
        return mapper.map(d, EmployeeDto.class);
    }
}
