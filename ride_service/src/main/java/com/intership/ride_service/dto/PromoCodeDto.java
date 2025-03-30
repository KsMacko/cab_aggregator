package com.intership.ride_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PromoCodeDto {
    private String code;
    @Schema(type = "integer", format = "int32")
    private Byte discount;
    private LocalDate validUntil;
}
