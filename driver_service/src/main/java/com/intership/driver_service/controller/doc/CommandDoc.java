package com.intership.driver_service.controller.doc;

import com.intership.driver_service.config.exception.BaseException;
import com.intership.driver_service.config.exception.BaseValidationException;
import com.intership.driver_service.dto.ProfileDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Tag(
        name = "Operations for managing driver profiles",
        description = "Endpoints for creating, updating, and deleting driver profiles"
)
@RequestMapping("/api/v1/drivers")
public interface CommandDoc {

    @Operation(
            summary = "Create a new driver profile",
            description = "Creates a new driver profile based on the provided profile data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Driver profile created successfully",
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PostMapping
    ResponseEntity<ProfileDto> createProfile(
            @Parameter(description = "Driver profile data to create", required = true)
            @Valid
            @RequestBody ProfileDto profileDto);

    @Operation(
            summary = "Update an existing driver profile",
            description = "Updates an existing driver profile with the provided data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver profile updated successfully",
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @PutMapping
    ProfileDto updateProfile(
            @Parameter(description = "Updated driver profile data", required = true)
            @RequestBody ProfileDto profileDto);

    @Operation(
            summary = "Delete a driver profile by ID",
            description = "Deletes a driver profile based on its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Driver profile deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProfile(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PathVariable Long id);
}