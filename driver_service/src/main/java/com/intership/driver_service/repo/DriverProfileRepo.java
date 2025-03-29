package com.intership.driver_service.repo;

import com.intership.driver_service.entity.DriverProfile;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverProfileRepo extends JpaRepository<DriverProfile, Long> {
    DriverProfile findByPhone(String phone);
    List<DriverProfile> findAllByFareType(FareType fareType, Sort sort);
    List<DriverProfile> findAllByDriverStatus(DriverStatus driverStatus, Sort sort);
    DriverStatus getDriverStatusByProfileId(Long profileId);
    FareType getFareTypeByProfileId(Long profileId);
    DriverProfile findDriverProfileByCarNumber(String carNumber);
    @Query("select p from DriverProfile p " +
            "join p.rates r " +
            "group by p.profileId " +
            "having floor(avg(r.value))=:rate")
    List<DriverProfile> findDriverProfileByRate(Byte rate, Sort sort);


}
