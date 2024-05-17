package com.assignment.moja_car_wash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MojaCarWashApplication {
    public static void main(String[] args) {

        SpringApplication.run(MojaCarWashApplication.class, args);
    }

}
