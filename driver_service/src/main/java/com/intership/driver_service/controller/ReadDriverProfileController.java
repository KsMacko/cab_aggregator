package com.intership.driver_service.controller;

import com.intership.driver_service.dto.DataPackageDto;
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
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class ReadDriverProfileController {
    private final ReadDriverProfileService readDriverProfileService;

    @GetMapping
    public DataPackageDto findAll(@RequestParam int page,
                                  @RequestParam int size,
                                  @RequestParam String orderBy,
                                  @RequestParam String direction) {
        return readDriverProfileService.readAllDriverProfiles(page, size, orderBy, direction);
    }
    @GetMapping("/{id}")
    public ProfileDto findById(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileById(id);
    }
    @GetMapping(params = "phone")
    public ProfileDto findByPhone(@RequestParam String phone) {
        return readDriverProfileService.readDriverProfileByPhone(phone);
    }
    @GetMapping(params = "carNumber")
    public ProfileDto findByCarNumber(@RequestParam String carNumber) {
        return readDriverProfileService.readDriverProfileByCarNumber(carNumber);
    }
    @GetMapping(params = "fare")
    public DataPackageDto findAllByFareType(@RequestParam String fare,
                                            @RequestParam int page,
                                            @RequestParam int size,
                                            @RequestParam String orderBy,
                                            @RequestParam String direction) {
        return readDriverProfileService.readDriverProfilesByFareRate(fare, page, size, orderBy, direction);
    }
    @GetMapping(params = "status")
    public DataPackageDto findAllByDriverStatus(@RequestParam String status,
                                                @RequestParam int page,
                                                @RequestParam int size,
                                                @RequestParam String orderBy,
                                                @RequestParam String direction) {
        return readDriverProfileService.readDriverProfilesByDriverStatus(status, page, size, orderBy, direction);
    }
    @GetMapping("/{id}/fare")
    public FareType findFareTypeByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileFareTypeById(id);
    }
    @GetMapping("/{id}/status")
    public DriverStatus findDriverStatusByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileStatusById(id);
    }
    @GetMapping("/{id}/rating")
    public Byte findRatingByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileRatingById(id);
    }
}
