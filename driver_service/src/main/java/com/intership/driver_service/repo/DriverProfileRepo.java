package com.intership.driver_service.repo;

import com.intership.driver_service.entity.DriverProfile;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverProfileRepo extends JpaRepository<DriverProfile, Long> {
    DriverProfile findByPhone(String phone);
    Page<DriverProfile> findAllByFareType(FareType fareType, Pageable pageable);
    Page<DriverProfile> findAllByDriverStatus(DriverStatus driverStatus, Pageable pageable);
    DriverStatus getDriverStatusByProfileId(Long profileId);
    FareType getFareTypeByProfileId(Long profileId);
    DriverProfile findDriverProfileByCarNumber(String carNumber);
    @Query("select p from DriverProfile p " +
            "join p.rates r " +
            "group by p.profileId " +
            "having floor(avg(r.value))=:rate")
    Page<DriverProfile> findDriverProfileByRate(Byte rate, Pageable pageable);


}
