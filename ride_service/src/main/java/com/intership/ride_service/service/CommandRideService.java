package com.intership.ride_service.service;

import com.intership.ride_service.config.exception.InvalidInputException;
import com.intership.ride_service.config.exception.ResourceNotFoundException;
import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.dto.mappers.RideMapper;
import com.intership.ride_service.entity.Ride;
import com.intership.ride_service.repo.RideRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandRideService {

    private final RideRepo rideRepo;
    @Transactional
    public RideDto createRide(RideDto rideDto) {
        Ride rideEntity = RideMapper.converter.handleDto(rideDto);
        return RideMapper.converter.handleEntity(rideRepo.save(rideEntity));
    }
    @Transactional
    public RideDto updateRide(RideDto updatedRideDto) {
        if (updatedRideDto.id() == null) {
            throw new InvalidInputException("ride.id.notNull");
        }
        Ride existingRide = rideRepo
                .findById(updatedRideDto.id())
                .orElseThrow(() -> new ResourceNotFoundException("ride.notFound"));
        RideMapper.converter.updateRideFromDto(updatedRideDto, existingRide);
        return RideMapper.converter.handleEntity( rideRepo.save(existingRide));
    }
    @Transactional
    public void deleteRide(String rideId) {
        if (!rideRepo.existsById(rideId)) throw new ResourceNotFoundException("ride.notFound");
        rideRepo.deleteById(rideId);
    }
}
