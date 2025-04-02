package com.intership.ride_service.dto.transfer_objects;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.Parameter;

public record RideFilterRequest(
        @Parameter(description = "Filter by ride date", example = "2023-10-01", required = false)
        LocalDate date,

        @Parameter(description = "Filter by driver id", example = "1", required = false)
        Long driverId,

        @Parameter(description = "Filter by passenger id", example = "2", required = false)
        Long passengerId,

        @Parameter(description = "Filter by ride status", example = "COMPLETED", required = false)
        String status,

        @Parameter(description = "Filter by fare type", example = "ECONOMY", required = false)
        String fareType,

        @Parameter(description = "Page number (zero-based)", example = "0", required = true)
        int page,

        @Parameter(description = "Number of items per page", example = "10", required = true)
        int size,

        @Parameter(description = "Field to sort by", example = "date", required = true)
        String sortBy,

        @Parameter(description = "Sort order (ASC or DESC)", example = "DESC", required = true)
        String order
) {}