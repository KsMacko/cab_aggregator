package com.intership.ride_service.dto;

import com.intership.ride_service.entity.enums.RideStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record RideDto (
    String id,
    PassengerDto passenger,
    DriverDto driver,
    PromoCodeDto promoCode,
    String startLocation,
    List<String> endLocation,
    LocalDate date,
    @Schema(type = "string", example = "12:30")
    LocalTime startTime,
    @Schema(type = "string", example = "12:30")
    LocalTime endTime,
    Integer distance,
    RideStatus status,
    BigDecimal price
    ){}
