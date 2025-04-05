package com.intership.ride_service.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PromoCode {
    private String code;
    private Byte discount;
    private LocalDate validUntil;
}
