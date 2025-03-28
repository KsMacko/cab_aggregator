package com.intership.passenger_service.repo;

import com.intership.passenger_service.entity.PassengerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerProfileRepo extends JpaRepository<PassengerProfile, Long> {
}
