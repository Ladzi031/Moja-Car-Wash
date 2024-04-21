package com.assignment.moja_car_wash.mapper.impl;

import com.assignment.moja_car_wash.domain.dto.CarDto;
import com.assignment.moja_car_wash.domain.entities.CarEntity;
import com.assignment.moja_car_wash.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements Mapper<CarDto, CarEntity> {

    private final ModelMapper modelMapper;

    public CustomerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CarEntity mapFromSource(CarDto s) {
        return modelMapper.map(s, CarEntity.class);
    }

    @Override
    public CarDto mapToSource(CarEntity d) {
        return modelMapper.map(d, CarDto.class);
    }
}
