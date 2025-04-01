package com.intership.ride_service.service;

import com.intership.ride_service.dto.PromoCodeDto;
import com.intership.ride_service.dto.PromoCodePackageDto;
import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.dto.RidePackageDto;
import com.intership.ride_service.dto.mappers.PromoCodeMapper;
import com.intership.ride_service.dto.mappers.RideMapper;
import com.intership.ride_service.entity.Ride;
import com.intership.ride_service.entity.enums.FareType;
import com.intership.ride_service.entity.enums.RideStatus;
import com.intership.ride_service.repo.RideRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadRideService {

    private final RideRepo rideRepo;

    public RideDto getRideById(String id) {
        Ride ride = rideRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        return RideMapper.converter.handleEntity(ride);
    }

    public RidePackageDto getAllRidesByDate(LocalDate date, int page, int size,String sortBy, String direction) {
        Page<Ride> rides = rideRepo.findByDate(date, createPageableObject(page, size, sortBy, direction));
        return createRidePackage(rides);
    }

    public RidePackageDto getAllRidesByDriver(Long driverId, int page, int size,String sortBy, String order) {
        Page<Ride> rides = rideRepo.findByDriverDriverId(driverId, createPageableObject(page, size, sortBy, order));
        return createRidePackage(rides);
    }

    public RidePackageDto getAllRidesByPassenger(Long passengerId, int page, int size,String sortBy, String order) {
        Page<Ride> rides = rideRepo.findByPassengerPassengerId(passengerId, createPageableObject(page, size, sortBy, order));
        return createRidePackage(rides);
    }

    public RidePackageDto getAllRidesByStatus(RideStatus status, int page, int size, String sortBy, String order) {
        Page<Ride> rides = rideRepo.findByStatus(status, createPageableObject(page, size, sortBy, order));
        return createRidePackage(rides);
    }

    public RidePackageDto getAllRidesByFareType(FareType fareType,int page, int size, String sortBy, String order) {
        Page<Ride> rides = rideRepo.findByDriverFareType(fareType, createPageableObject(page, size, sortBy, order));
        return createRidePackage(rides);
    }

    public PromoCodePackageDto getUsedPromoCodes(int page, int size, String sortBy, String order) {
        Page<Ride> rides = rideRepo.findByPromoCodeIsNotNull(createPageableObject(page, size, sortBy, order));
        List<PromoCodeDto> promoCodesDto = rides.getContent()
                .stream()
                .map(ride -> PromoCodeMapper.converter.handleEntity(ride.getPromoCode()))
                .toList();
        return new PromoCodePackageDto(
                promoCodesDto,
                rides.getTotalElements(),
                rides.getNumber(),
                rides.getSize(),
                rides.getTotalPages()
        );
    }

    public Pageable createPageableObject(int page, int size, String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        return PageRequest.of(page, size, sort);
    }
    public RidePackageDto createRidePackage(Page<Ride> rides) {
        List<RideDto> ridesDto = rides.getContent()
                .stream()
                .map(RideMapper.converter::handleEntity)
                .toList();
        return new RidePackageDto(
                ridesDto,
                rides.getTotalElements(),
                rides.getNumber(),
                rides.getSize(),
                rides.getTotalPages()
        );
    }
}