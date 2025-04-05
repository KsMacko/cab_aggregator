package com.intership.ride_service.dto;

import com.intership.ride_service.enums.FareType;

public record DriverDto (
        Long driverId,
        String firstName,
        String lastName,
        FareType fareType,
        CarDto car
){}
