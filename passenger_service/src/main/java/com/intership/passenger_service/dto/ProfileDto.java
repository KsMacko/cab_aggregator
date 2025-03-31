package com.intership.passenger_service.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public record ProfileDto (
    Long profileId,
    String firstName,
    String email,
    String phone,
    @Schema(type = "integer", format = "int32")
    Byte rate
){}
