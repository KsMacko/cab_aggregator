package com.intership.driver_service.repo;

import com.intership.driver_service.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RateRepo extends JpaRepository<Rate, Long> {
    @Query("select FLOOR(AVG(r.value)) from Rate r where r.driver.profileId=:profileId")
    Byte findDriverRatingByProfileId(Long profileId);
}
