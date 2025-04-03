package com.intership.ride_service.dto;

import java.time.LocalDate;

public record PromoCodeDto (
        String code,
        Byte discount,
        LocalDate validUntil
){}
