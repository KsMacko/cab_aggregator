package com.intership.ride_service.repo;

import com.intership.ride_service.entity.Ride;
import com.intership.ride_service.entity.enums.FareType;
import com.intership.ride_service.entity.enums.RideStatus;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface RideRepo extends MongoRepository<Ride, String> {

    Page<Ride> findByDate(LocalDate date, Pageable pageable);
    Page<Ride> findByDriverDriverId(Long driverId, Pageable pageable);
    Page<Ride> findByPassengerPassengerId(Long passengerId, Pageable pageable);
    Page<Ride> findByStatus(RideStatus status,Pageable pageable);
    Page<Ride> findByDriverFareType(FareType fareType, Pageable pageable);
    Page<Ride> findByPromoCodeIsNotNull(Pageable pageable);
}