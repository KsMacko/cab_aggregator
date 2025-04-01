package com.intership.driver_service.dto;

import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record ProfileDto (
    Long profileId,
    String firstName,
    String lastName,
    String carNumber,
    String carDescription,
    FareType fareType,
    DriverStatus driverStatus,
    String phone
){}

