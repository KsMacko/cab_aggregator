package com.intership.ride_service.dto.mappers;

import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.entity.Ride;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RideMapper {

    RideMapper converter = Mappers.getMapper(RideMapper.class);

    @Mapping(source = "passenger.passengerId", target = "passenger.passengerId")
    @Mapping(source = "driver.driverId", target = "driver.driverId")
    @Mapping(source = "promoCode.code", target = "promoCode.code")
    Ride handleDto(RideDto rideDto);

    @Mapping(source = "passenger.passengerId", target = "passenger.passengerId")
    @Mapping(source = "driver.driverId", target = "driver.driverId")
    @Mapping(source = "promoCode.code", target = "promoCode.code")
    RideDto handleEntity(Ride ride);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRideFromDto(RideDto dto, @MappingTarget Ride entity);
}
