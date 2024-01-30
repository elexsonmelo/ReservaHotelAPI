package com.example.reservahotelapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReservaHotelApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservaHotelApiApplication.class, args);
    }

}
