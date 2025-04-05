package com.intership.ride_service.dto;

import java.util.List;

public record PromoCodePackageDto (
   List<PromoCodeDto> promoCodeDtoList,
   long totalElements,
   int pageNumber,
   int pageSize,
   int totalPages
) {}
