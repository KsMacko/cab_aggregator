package com.intership.driver_service.repo;

import com.intership.driver_service.entity.DriverAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverAccountRepo extends JpaRepository<DriverAccount, Long> {

}
