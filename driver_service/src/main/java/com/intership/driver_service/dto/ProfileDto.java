package com.intership.driver_service.dto;

import com.intership.driver_service.config.ValidationConstants;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProfileDto(
        @Positive(message = "driver.id.invalidInput")
        Long profileId,

        @Size(max = ValidationConstants.MAX_NAME_LENGTH, message = "firstName.size")
        @Pattern(regexp = ValidationConstants.CYRILLIC_REGEX, message = "firstName.invalidInput")
        @NotBlank(message = "firstName.notNull")
        String firstName,

        @Size(max = ValidationConstants.MAX_NAME_LENGTH, message = "lastName.size")
        @Pattern(regexp = ValidationConstants.CYRILLIC_REGEX, message = "lastName.invalidInput")
        @NotBlank(message = "lastName.notNull")
        String lastName,

        @Size(max = ValidationConstants.MAX_CAR_NUMBER_LENGTH, message = "driver.car.number.size")
        @Pattern(regexp = ValidationConstants.CAR_NUM_PATTERN, message = "driver.car.number.invalidInput")
        @NotBlank(message = "driver.car.number.notNull")
        String carNumber,

        @Size(max = ValidationConstants.MAX_CAR_DESCRIPTION_LENGTH, message = "driver.car.description.size")
        @Pattern(regexp = ValidationConstants.CAR_DESCRIPTION_REGEX, message = "driver.car.description.invalidInput")
        @NotBlank(message = "driver.car.description.notNull")
        String carDescription,

        @NotNull(message = "driver.fare.notNull")
        FareType fareType,

        @NotNull(message = "driver.status.notNull")
        DriverStatus driverStatus,

        @Size(max = ValidationConstants.MAX_PHONE_LENGTH, message = "driver.phone.size")
        @Pattern(regexp = ValidationConstants.PHONE_PATTERN, message = "driver.phone.invalidInput")
        @NotBlank(message = "driver.phone.notNull")
        String phone,

        @Schema(hidden = true)
        Byte rate
) {}