package com.intership.ride_service.controller;

import com.intership.ride_service.dto.PromoCodePackageDto;
import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.dto.RidePackageDto;
import com.intership.ride_service.entity.enums.FareType;
import com.intership.ride_service.entity.enums.RideStatus;
import com.intership.ride_service.service.ReadRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/rides")
@RequiredArgsConstructor
public class ReadRideController {

    private final ReadRideService readRideService;

    @GetMapping("/{id}")
    public RideDto getRideById(@PathVariable String id) {
        return readRideService.getRideById(id);
    }

    @GetMapping(params = "date")
    public RidePackageDto getAllRidesByDate(
            @RequestParam LocalDate date,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "startTime") String sortBy,
            @RequestParam(defaultValue = "ASC") String order) {
        return readRideService.getAllRidesByDate(date, page, size, sortBy, order);
    }

    @GetMapping("/driver/{id}")
    public RidePackageDto getAllRidesByDriver(
            @PathVariable Long id,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getAllRidesByDriver(id,  page, size, sortBy, order);
    }

    @GetMapping("/passenger/{id}")
    public RidePackageDto getAllRidesByPassenger(
            @PathVariable Long id,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getAllRidesByPassenger(id,  page, size, sortBy, order);
    }

    @GetMapping(params = "status")
    public RidePackageDto getAllRidesByStatus(
            @RequestParam RideStatus status,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getAllRidesByStatus(status,  page, size, sortBy, order);
    }

    @GetMapping(params = "fare")
    public RidePackageDto getAllRidesByFareType(
            @RequestParam FareType fareType,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getAllRidesByFareType(fareType,  page, size, sortBy, order);
    }

    @GetMapping("/promo-codes")
    public PromoCodePackageDto getUsedPromoCodes(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "validUntil") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getUsedPromoCodes( page, size, sortBy, order);
    }
}
