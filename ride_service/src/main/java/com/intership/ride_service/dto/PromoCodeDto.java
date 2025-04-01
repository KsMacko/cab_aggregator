package com.intership.ride_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;


public record PromoCodeDto (
    String code,
    @Schema(type = "integer", format = "int32")
    Byte discount,
    LocalDate validUntil
){}
