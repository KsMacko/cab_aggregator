package com.intership.passenger_service.dto;


import java.util.List;

public record DataPackageDto(
        List<ProfileDto> profiles,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {}
