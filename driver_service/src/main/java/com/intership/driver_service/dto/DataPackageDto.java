package com.intership.driver_service.dto;

import java.util.List;

public record DataPackageDto (
        List<ProfileDto> profilesDto,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
){ }
