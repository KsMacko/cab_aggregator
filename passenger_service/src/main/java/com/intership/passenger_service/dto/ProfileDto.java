package com.intership.passenger_service.dto;


import com.intership.passenger_service.config.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProfileDto (
        @Positive(message = "passenger.id.invalidInput")
        Long profileId,
        @Size(max = ValidationConstants.MAX_NAME_LENGTH, message = "passenger.name.size")
        @Pattern(regexp = ValidationConstants.CYRILLIC_REGEX, message = "passenger.name.invalidInput")
        @NotNull(message = "passenger.name.notNull")
        String firstName,
        @Schema(example = "example@gmail.com")
        @Pattern(regexp = ValidationConstants.EMAIL_PATTERN, message = "passenger.email.invalidInput")
        @Size(max = ValidationConstants.MAX_EMAIL_LENGTH)
        String email,
        @Pattern(regexp = ValidationConstants.PHONE_PATTERN, message = "passenger.phone.invalidInput")
        @Size(max = ValidationConstants.MAX_PHONE_LENGTH)
        @Schema(example = "375336666666")
        String phone,
        @Schema(hidden = true)
        Byte rate
){}
