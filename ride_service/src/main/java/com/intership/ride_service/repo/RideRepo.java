package com.intership.ride_service.repo;

import com.intership.ride_service.entity.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RideRepo extends MongoRepository<Ride, String> {
    Page<Ride> findByPromoCodeIsNotNull(Pageable pageable);
}