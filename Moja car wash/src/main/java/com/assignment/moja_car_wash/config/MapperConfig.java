package com.assignment.moja_car_wash.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /*
    * Converter<String, LocalTime> stringLocalTimeConverter = new AbstractConverter<String, LocalTime>() {
            @Override
            protected LocalTime convert(String s) {
                return LocalTime.parse(s);
            }
        };
        modelMapper.addConverter(stringLocalTimeConverter);*/
}
