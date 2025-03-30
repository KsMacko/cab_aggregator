package com.intership.ride_service.dto;

import com.intership.ride_service.entity.Car;
import com.intership.ride_service.entity.enums.FareType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDto {
    private Long driverId;
    private String firstName;
    private String lastName;
    private FareType fareType;
    private Car car;
}
