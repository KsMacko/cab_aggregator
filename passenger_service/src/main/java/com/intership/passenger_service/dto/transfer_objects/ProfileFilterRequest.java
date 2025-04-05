package com.intership.passenger_service.dto.transfer_objects;

import com.intership.passenger_service.config.ValidationConstants;
import com.intership.passenger_service.enums.FieldsToSort;
import com.intership.passenger_service.enums.OrderDirection;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProfileFilterRequest (
        @Schema(description = "Filter by email", example = "example@gmail.com")
        @Pattern(regexp = ValidationConstants.EMAIL_PATTERN)
        @Size(max = 50)
        String email,
        @Schema(description = "Filter by phone")
        @Pattern(regexp = ValidationConstants.PHONE_PATTERN)
        @Size(max = 12)
        String phone,
        @Schema(type = "integer", format = "int32",description = "Filter by rate", example = "4")
        @Min(1)
        @Max(5)
        Byte rate,
        @Schema(description = "Page number (zero-based)", example = "0", required = true)
        int page,
        @Schema(description = "Number of items per page", example = "10", required = true)
        int size,
        @Schema(description = "Field to sort by", example = "firstName", required = true)
        FieldsToSort sortBy,
        @Schema(description = "Sort order (ASC or DESC)", example = "DESC", required = true)
        OrderDirection order
){ }
