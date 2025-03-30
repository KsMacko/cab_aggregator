package com.intership.ride_service.dto;

import com.intership.ride_service.entity.enums.RideStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RideDto {
    private String id;
    private PassengerDto passenger;
    private DriverDto driver;
    private PromoCodeDto promoCode;
    private String startLocation;
    private List<String> endLocation;
    private LocalDate date;
    @Schema(type = "string", example = "12:30")
    private LocalTime startTime;
    @Schema(type = "string", example = "12:30")
    private LocalTime endTime;
    private Integer distance;
    private RideStatus status;
    private BigDecimal price;
}
