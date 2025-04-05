package com.intership.passenger_service.controller.doc;

import com.intership.passenger_service.config.exception.BaseException;
import com.intership.passenger_service.config.exception.BaseValidationException;
import com.intership.passenger_service.dto.transfer_objects.DataPackageDto;
import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.transfer_objects.ProfileFilterRequest;
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

@Tag(
        name = "Operations for reading passenger data",
        description = "Endpoints for retrieving profile-related information")
@RequestMapping("api/v1/passengers")
public interface ReadDoc {
    @Operation(
            summary = "Get passenger profile by id",
            description = "Retrieves complete profile data by its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile found",
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Occurs because of the incorrect input of id",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the passenger is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}")
    ProfileDto readPassengerById(
            @Parameter(description = "Unique identifier of the passenger", example = "1", required = true)
            @PathVariable Long id);
    @Operation(
            summary = "Get all profiles with filters",
            description = "Retrieves a paginated and sorted list of passenger profiles based on various filter criteria"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rides retrieved successfully",
                    content = @Content(schema = @Schema(implementation = DataPackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })

    @GetMapping
    DataPackageDto readAllProfiles(@ParameterObject ProfileFilterRequest filterRequest);

    @Operation(
            summary = "Get rate by Passenger",
            description = "Retrieves a rate by passenger"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Rate retrieved successfully",
                    content = @Content(schema = @Schema(implementation = Byte.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Passenger not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}/rate")
    Byte readRating(
            @Parameter(description = "Unique identifier of the passenger", example = "1", required = true)
            @PathVariable Long id);
}
