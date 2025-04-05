package com.intership.ride_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.intership.ride_service.enums.RideStatus;
import com.mongodb.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
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
    private Passenger passenger;
    private Driver driver;
    @Nullable
    private PromoCode promoCode;
    private String startLocation;
    private List<String> endLocation;
    @CreationTimestamp
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;
    private Float distance;
    private RideStatus status;
    private BigDecimal price;

}
