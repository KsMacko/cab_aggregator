package com.intership.ride_service.entity;

import com.intership.ride_service.enums.FareType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver {
    private Long driverId;
    private String firstName;
    private String lastName;
    private FareType fareType;
    private Car car;
}