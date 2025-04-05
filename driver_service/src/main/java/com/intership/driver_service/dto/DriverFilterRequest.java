package com.intership.driver_service.dto;

import com.intership.driver_service.config.ValidationConstants;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import com.intership.driver_service.enums.FieldFilter;
import com.intership.driver_service.enums.OrderDirection;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DriverFilterRequest(
        @Parameter(description = "Filter by phone", example = "375336666666")
        @Size(max = ValidationConstants.MAX_PHONE_LENGTH, message = "driver.phone.size")
        @Pattern(regexp = ValidationConstants.PHONE_PATTERN, message = "driver.phone.invalidInput")
        String phone,

        @Parameter(description = "Filter by car number", example = "0678HT8")
        @Size(max = ValidationConstants.MAX_CAR_NUMBER_LENGTH, message = "driver.car.number.size")
        @Pattern(regexp = ValidationConstants.CAR_NUM_PATTERN, message = "driver.car.number.invalidInput")
        String carNumber,

        @Parameter(description = "Filter by driver's first name", example = "Иван")
        @Size(max = ValidationConstants.MAX_NAME_LENGTH, message = "firstName.size")
        @Pattern(regexp = ValidationConstants.CYRILLIC_REGEX, message = "firstName.invalidInput")
        String firstName,

        @Parameter(description = "Filter by driver's last name", example = "Иванов")
        @Size(max = ValidationConstants.MAX_NAME_LENGTH, message = "lastName.size")
        @Pattern(regexp = ValidationConstants.CYRILLIC_REGEX, message = "lastName.invalidInput")
        String lastName,

        @Parameter(description = "Filter by driver's status", example = "FREE")
        DriverStatus status,

        @Parameter(description = "Filter by driver's fare type", example = "ECONOMY")
        FareType fareType,

        @Schema(
                type = "integer",
                format = "int32",
                description = "Оценка водителя (число от 1 до 5)",
                example = "4"
        )
        @Min(ValidationConstants.MIN_RATE)
        @Max(ValidationConstants.MAX_RATE)
        Byte rate,

        @Parameter(description = "Page number (zero-based)", example = "0", required = true)
        @Positive(message = "error.invalidInput")
        @NotNull(message = "error.invalidInput")
        int page,

        @Parameter(description = "Number of items per page", example = "10", required = true)
        @Positive(message = "error.invalidInput")
        @NotNull(message = "error.invalidInput")
        int size,

        @Parameter(description = "Field to sort by", example = "firstName", required = true)
        @NotNull(message = "error.invalidInput")
        FieldFilter sortBy,

        @Parameter(description = "Sort order (ASC or DESC)", example = "DESC", required = true)
        @NotNull(message = "error.invalidInput")
        OrderDirection order
) {}