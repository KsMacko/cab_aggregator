package com.intership.ride_service.dto;

import java.util.List;

public record RidePackageDto (
    List<RideDto> ridesDto,
    long totalElements,
    int pageNumber,
    int pageSize,
    int totalPages
    ){}
