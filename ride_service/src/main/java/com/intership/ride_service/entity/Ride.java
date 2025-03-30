package com.intership.ride_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intership.ride_service.entity.enums.RideStatus;
import com.mongodb.lang.Nullable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Document(collection = "rides")
@Getter
@Setter
public class Ride {
    @Id
    private String id;
    @NotNull
    private Passenger passenger;
    @NotNull
    private Driver driver;
    @Nullable
    private PromoCode promoCode;
    private String startLocation;
    private List<String> endLocation;
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;
    private Integer distance;
    private RideStatus status;
    private BigDecimal price;

}
