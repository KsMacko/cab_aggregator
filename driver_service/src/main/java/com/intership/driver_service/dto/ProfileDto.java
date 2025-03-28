package com.intership.driver_service.dto;

import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long profileId;
    private String firstName;
    private String lastName;
    private String carNumber;
    private String carDescription;
    private FareType fareType;
    private DriverStatus driverStatus;
    private String phone;
}

