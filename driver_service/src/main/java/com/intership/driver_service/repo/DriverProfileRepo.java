package com.intership.driver_service.repo;

import com.intership.driver_service.entity.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverProfileRepo extends JpaRepository<DriverProfile, Long> {
}
