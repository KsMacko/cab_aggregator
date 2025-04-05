package com.intership.driver_service.dto.mapper;

import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.entity.DriverProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper converter = Mappers.getMapper(ProfileMapper.class);

    DriverProfile handleDto(ProfileDto profileDto);
    ProfileDto handleEntity(DriverProfile driverProfile);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfileFromDto(ProfileDto dto, @MappingTarget DriverProfile entity);
}
