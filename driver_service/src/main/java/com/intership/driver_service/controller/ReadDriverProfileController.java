package com.intership.driver_service.controller;

import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import com.intership.driver_service.service.ReadDriverProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class ReadDriverProfileController {
    private final ReadDriverProfileService readDriverProfileService;

    @GetMapping("/read-all")
    public List<ProfileDto> findAll(@RequestParam String orderBy,
                                    @RequestParam String order) {
        return readDriverProfileService.readAllDriverProfiles(orderBy, order);
    }
    @GetMapping("/read-by-id/{id}")
    public ProfileDto findById(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileById(id);
    }
    @GetMapping("/read-by-phone")
    public ProfileDto findByPhone(@RequestParam String phone) {
        return readDriverProfileService.readDriverProfileByPhone(phone);
    }
    @GetMapping("/read-by-car-number")
    public ProfileDto findByCarNumber(@RequestParam String carNumber) {
        return readDriverProfileService.readDriverProfileByCarNumber(carNumber);
    }
    @GetMapping("/read-all-by-fare")
    public List<ProfileDto> findAllByFareType(@RequestParam String fare,
                                              @RequestParam String orderBy,
                                              @RequestParam String order) {
        return readDriverProfileService.readDriverProfilesByFareRate(fare, orderBy, order);
    }
    @GetMapping("/read-all-by-status")
    public List<ProfileDto> findAllByDriverStatus(@RequestParam String status,
                                                  @RequestParam String orderBy,
                                                  @RequestParam String order) {
        return readDriverProfileService.readDriverProfilesByDriverStatus(status, orderBy, order);
    }
    @GetMapping("/read-fare-by-driver-id/{id}")
    public FareType findFareTypeByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileFareTypeById(id);
    }
    @GetMapping("/read-status-by-driver-id/{id}")
    public DriverStatus findDriverStatusByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileStatusById(id);
    }
    @GetMapping("/read-rating-by-driver-id/{id}")
    public Byte findRatingByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileRatingById(id);
    }
    @GetMapping("/read-all-by-rate")
    public List<ProfileDto> findAllByDriverStatus(@RequestParam Byte rate,
                                                  @RequestParam String orderBy,
                                                  @RequestParam String order) {
        return readDriverProfileService.readDriverProfilesByRate(rate, orderBy, order);
    }

}
