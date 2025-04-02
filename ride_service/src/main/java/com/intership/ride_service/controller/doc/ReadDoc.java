package com.intership.ride_service.controller.doc;

import com.intership.ride_service.config.exception.BaseExceptionDto;
import com.intership.ride_service.dto.PromoCodePackageDto;
import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.dto.transfer_objects.RideFilterRequest;
import com.intership.ride_service.dto.transfer_objects.RidePackageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Tag(name = "Operations for reading ride data", description = "Endpoints for retrieving ride-related information")
@RequestMapping("api/v1/rides")
public interface ReadDoc {

    @Operation(
            summary = "Get ride by id",
            description = "Retrieves complete ride data by its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ride found",
                    content = @Content(schema = @Schema(implementation = RideDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride data is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @GetMapping("/{id}")
    RideDto getRideById(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @PathVariable String id);

    @Operation(
            summary = "Get all rides with filters",
            description = "Retrieves a paginated and sorted list of rides based on various filter criteria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rides retrieved successfully",
                    content = @Content(schema = @Schema(implementation = RidePackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })

    @GetMapping
    RidePackageDto getAllRides(@ParameterObject RideFilterRequest filterRequest);

    @Operation(
            summary = "Get used promo codes",
            description = "Retrieves a paginated and sorted list of promo codes that have been used in rides"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Promo codes retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PromoCodePackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @GetMapping("/promo-codes")
    PromoCodePackageDto getUsedPromoCodes(
            @Parameter(description = "Page number (zero-based)", example = "0", required = true)
            @RequestParam int page,

            @Parameter(description = "Number of items per page", example = "10", required = true)
            @RequestParam int size,

            @Parameter(description = "Field to sort by", example = "validUntil", required = true)
            @RequestParam(defaultValue = "validUntil") String sortBy,

            @Parameter(description = "Sort order (ASC or DESC)", example = "DESC", required = true)
            @RequestParam(defaultValue = "DESC") String order);
}