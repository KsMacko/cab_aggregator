package com.intership.ride_service.repo;

import com.intership.ride_service.entity.Ride;
import com.intership.ride_service.entity.enums.FareType;
import com.intership.ride_service.entity.enums.RideStatus;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface RideRepo extends MongoRepository<Ride, String> {

    List<Ride> findByDate(LocalDate date, Sort sort);
    List<Ride> findByDriverDriverId(Long driverId, Sort sort);
    List<Ride> findByPassengerPassengerId(Long passengerId, Sort sort);
    List<Ride> findByStatus(RideStatus status, Sort sort);
    List<Ride> findByDriverFareType(FareType fareType, Sort sort);
    List<Ride> findByPromoCodeIsNotNull(Sort sort);
}