package com.intership.driver_service.controller.doc;

import com.intership.driver_service.config.exception.BaseException;
import com.intership.driver_service.config.exception.BaseValidationException;
import com.intership.driver_service.dto.DataPackageDto;
import com.intership.driver_service.dto.DriverFilterRequest;
import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Operations for retrieving driver profile data",
        description = "Endpoints for reading driver profile information"
)
@RequestMapping("/api/v1/drivers")
public interface ReadDoc {

    @Operation(
            summary = "Find all driver profiles",
            description = "Retrieves a paginated list of driver profiles based on filters"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of driver profiles retrieved successfully",
                    content = @Content(schema = @Schema(implementation = DataPackageDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input parameters",
                    content = @Content(schema = @Schema(implementation = BaseValidationException.class))
            )
    })
    @GetMapping
    DataPackageDto findAllDrivers(
            @ModelAttribute
            @Parameter(description = "Filter options for driver profiles", required = true)
            DriverFilterRequest filter);

    @Operation(
            summary = "Find a driver profile by ID",
            description = "Retrieves a driver profile based on its unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver profile retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ProfileDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}")
    ProfileDto findById(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PathVariable Long id);

    @Operation(
            summary = "Find fare type by driver ID",
            description = "Retrieves the fare type of a driver based on their unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fare type retrieved successfully",
                    content = @Content(schema = @Schema(implementation = FareType.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}/fare")
    FareType findFareTypeByDriverId(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PathVariable Long id);

    @Operation(
            summary = "Find driver status by driver ID",
            description = "Retrieves the status of a driver based on their unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver status retrieved successfully",
                    content = @Content(schema = @Schema(implementation = DriverStatus.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}/status")
    DriverStatus findDriverStatusByDriverId(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PathVariable Long id);

    @Operation(
            summary = "Find driver rating by driver ID",
            description = "Retrieves the rating of a driver based on their unique identifier"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Driver rating retrieved successfully",
                    content = @Content(schema = @Schema(type = "integer", format = "byte"))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Occurs when the driver profile is not found",
                    content = @Content(schema = @Schema(implementation = BaseException.class))
            )
    })
    @GetMapping("/{id}/rating")
    Byte findRatingByDriverId(
            @Parameter(description = "Unique identifier of the driver profile", example = "1", required = true)
            @PathVariable Long id);
}