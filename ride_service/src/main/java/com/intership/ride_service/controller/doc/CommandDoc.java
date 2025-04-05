package com.intership.ride_service.controller.doc;

import com.intership.ride_service.config.exception.BaseExceptionDto;
import com.intership.ride_service.config.exception.BaseValidationException;
import com.intership.ride_service.dto.RideDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Operations for managing ride data",
        description = "Endpoints for creating, updating, and deleting ride-related information")
@RequestMapping("/api/v1/rides")
public interface CommandDoc {

    @Operation(
            summary = "Create a new ride",
            description = "Creates a new ride based on the provided ride data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Ride created successfully",
                    content = @Content(schema = @Schema(implementation = RideDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PostMapping
    ResponseEntity<RideDto> createPassenger(
            @Parameter(description = "Ride data to create", required = true)
            @Valid
            @RequestBody RideDto rideDto);

    @Operation(
            summary = "Delete a ride by ID",
            description = "Deletes a ride based on its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Ride deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @DeleteMapping
    ResponseEntity<Void> deleteRide(
            @Parameter(description = "Unique identifier of the ride", example = "67e92415198ed4652992f5ec", required = true)
            @RequestParam String id);

    @Operation(
            summary = "Update an existing ride",
            description = "Updates an existing ride with the provided data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Ride updated successfully",
                    content = @Content(schema = @Schema(implementation = RideDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the ride is not found",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseExceptionDto.class))
            )
    })
    @PutMapping
    RideDto updateRide(
            @Parameter(description = "Updated ride data", required = true)
            @RequestBody RideDto ride);
}
