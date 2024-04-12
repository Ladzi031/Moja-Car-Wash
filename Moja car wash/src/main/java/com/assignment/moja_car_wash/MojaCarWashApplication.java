package com.assignment.moja_car_wash;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Log
@EnableScheduling
public class MojaCarWashApplication  {
    public static void main(String[] args) {

        SpringApplication.run(MojaCarWashApplication.class, args);
    }

}
