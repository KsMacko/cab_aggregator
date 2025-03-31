package com.intership.passenger_service.repo;

import com.intership.passenger_service.entity.PassengerProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PassengerProfileRepo extends JpaRepository<PassengerProfile, Long> {
    @Query("select p from PassengerProfile p join p.rates r group by p.profileId having floor(avg(r.value))=:rate")
    Page<PassengerProfile> findPassengerProfileByRate(Byte rate, Pageable pageable);
    Optional<PassengerProfile> findByEmail(String email);
    Optional<PassengerProfile> findByPhone(String phone);
    @Query("SELECT p.activatedPromoCodeId FROM PassengerProfile p WHERE p.profileId = :passengerId")
    Optional<Long> findActivatedPromoCodeIdByPassenger(@Param("passengerId") Long passengerId);
}
