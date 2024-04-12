package com.assignment.moja_car_wash.mapper.impl;

import com.assignment.moja_car_wash.domain.dto.CustomerDto;
import com.assignment.moja_car_wash.domain.entities.CustomerEntity;
import com.assignment.moja_car_wash.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements Mapper<CustomerDto, CustomerEntity> {

    private final ModelMapper modelMapper;

    public CustomerMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerEntity mapFromSource(CustomerDto s) {
        return modelMapper.map(s, CustomerEntity.class);
    }

    @Override
    public CustomerDto mapToSource(CustomerEntity d) {
        return modelMapper.map(d, CustomerDto.class);
    }
}
