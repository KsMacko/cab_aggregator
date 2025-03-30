package com.intership.ride_service.dto.mappers;

import com.intership.ride_service.dto.PromoCodeDto;
import com.intership.ride_service.entity.PromoCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromoCodeMapper {
    PromoCodeMapper converter = Mappers.getMapper(PromoCodeMapper.class);

    PromoCodeDto handleEntity(PromoCode promoCode);

    PromoCode handleDto(PromoCodeDto promoCodeDto);
}
