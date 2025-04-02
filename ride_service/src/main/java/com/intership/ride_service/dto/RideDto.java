package com.intership.ride_service.dto;

import com.intership.ride_service.entity.enums.RideStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RideDto(
        String id,
        @NotNull(message = "ride.passenger.notNull")
        PassengerDto passenger,
        @NotNull(message = "ride.driver.notNull")
        DriverDto driver,
        @Schema(description = "Promo code applied to the ride", nullable = true)
        PromoCodeDto promoCode,
        @NotBlank(message = "ride.startLocation.notBlank")
        String startLocation,
        @NotEmpty(message = "ride.endLocation.notEmpty")
        List<String> endLocation,
        @Schema(description = "Date of the ride (automatically)", nullable = true)
        LocalDate date,
        @Schema(description = "Start time of the ride", example = "10:00")
        @NotNull(message = "ride.startTime.notNull")
        LocalTime startTime,
        @Schema(description = "End time of the ride", example = "11:00")
        @NotNull(message = "ride.endTime.notNull")
        LocalTime endTime,
        @Schema(description = "Distance of the ride in kilometers", example = "15")
        @Positive(message = "ride.distance.min")
        Float distance,
        @Schema(description = "Status of the ride", example = "COMPLETED")
        @NotNull(message = "ride.status.notNull")
        RideStatus status,
        @Schema(description = "Price of the ride", example = "150.00")
        @Positive(message = "ride.price.min")
        BigDecimal price
) {}
