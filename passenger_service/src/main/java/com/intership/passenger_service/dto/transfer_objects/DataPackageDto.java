package com.intership.passenger_service.dto.transfer_objects;


import com.intership.passenger_service.dto.ProfileDto;

import java.util.List;

public record DataPackageDto(
        List<ProfileDto> profiles,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {}
