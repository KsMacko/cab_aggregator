package com.intership.ride_service.service;

import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.dto.mappers.RideMapper;
import com.intership.ride_service.entity.Ride;
import com.intership.ride_service.repo.RideRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandRideService {

    private final RideRepo rideRepo;

    public RideDto createRide(RideDto rideDto) {
        Ride rideEntity = RideMapper.converter.handleDto(rideDto);
        return RideMapper.converter.handleEntity(rideRepo.save(rideEntity));
    }

    public RideDto updateRide(RideDto updatedRideDto) {
        if (updatedRideDto.getId() == null) {
            throw new IllegalArgumentException("Ride ID must not be null");
        }
        Ride existingRide = rideRepo.findById(updatedRideDto.getId())
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        RideMapper.converter.updateRideFromDto(updatedRideDto, existingRide);

        return RideMapper.converter.handleEntity( rideRepo.save(existingRide));
    }

    public void deleteRide(String rideId) {
        rideRepo.deleteById(rideId);
    }
}
