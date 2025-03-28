package com.intership.passenger_service.dto.mapper;

import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.entity.PassengerProfile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper convertor = Mappers.getMapper(ProfileMapper.class);

    PassengerProfile handleDto(ProfileDto profileDto);
    ProfileDto handleEntity(PassengerProfile passengerProfile);
}
