package com.intership.ride_service.service;

import com.intership.ride_service.dto.PromoCodeDto;
import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.dto.mappers.PromoCodeMapper;
import com.intership.ride_service.dto.mappers.RideMapper;
import com.intership.ride_service.entity.Ride;
import com.intership.ride_service.entity.enums.FareType;
import com.intership.ride_service.entity.enums.RideStatus;
import com.intership.ride_service.repo.RideRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadRideService {

    private final RideRepo rideRepo;

    public RideDto getRideById(String id) {
        Ride ride = rideRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        return RideMapper.converter.handleEntity(ride);
    }

    public List<RideDto> getAllRidesByDate(LocalDate date, String sortBy, String order) {
        Sort sort = createSort(sortBy, order);
        return rideRepo.findByDate(date, sort).stream()
                .map(RideMapper.converter::handleEntity)
                .toList();
    }

    public List<RideDto> getAllRidesByDriver(Long driverId, String sortBy, String order) {
        Sort sort = createSort(sortBy, order);
        return rideRepo.findByDriverDriverId(driverId, sort).stream()
                .map(RideMapper.converter::handleEntity)
                .toList();
    }

    public List<RideDto> getAllRidesByPassenger(Long passengerId, String sortBy, String order) {
        Sort sort = createSort(sortBy, order);
        return rideRepo.findByPassengerPassengerId(passengerId, sort).stream()
                .map(RideMapper.converter::handleEntity)
                .toList();
    }

    public List<RideDto> getAllRidesByStatus(RideStatus status, String sortBy, String order) {
        Sort sort = createSort(sortBy, order);
        return rideRepo.findByStatus(status, sort).stream()
                .map(RideMapper.converter::handleEntity)
                .toList();
    }

    public List<RideDto> getAllRidesByFareType(FareType fareType, String sortBy, String order) {
        Sort sort = createSort(sortBy, order);
        return rideRepo.findByDriverFareType(fareType, sort).stream()
                .map(RideMapper.converter::handleEntity)
                .toList();
    }

    public List<PromoCodeDto> getUsedPromoCodes(String sortBy, String order) {
        Sort sort = createSort(sortBy, order);
        return rideRepo.findByPromoCodeIsNotNull(sort).stream()
                .filter(ride -> ride.getPromoCode() != null)
                .map(ride -> PromoCodeMapper.converter.handleEntity(ride.getPromoCode()))
                .toList();
    }

    public Sort createSort(String orderBy, String direction) {
        Sort.Direction direct = Sort.Direction.fromString(direction);
        return Sort.by(direct, orderBy);
    }
}