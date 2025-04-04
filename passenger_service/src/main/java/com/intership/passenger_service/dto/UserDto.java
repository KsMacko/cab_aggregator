package com.intership.passenger_service.dto;

import com.intership.passenger_service.config.ValidationConstants;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDto(
        @Pattern(regexp = ValidationConstants.ALPHANUMERIC_REGEX)
        @Size(max = 20)
        String username,
        @Pattern(regexp = ValidationConstants.ALPHANUMERIC_REGEX)
        @Size(max = 20)
        String password
)
{ }
