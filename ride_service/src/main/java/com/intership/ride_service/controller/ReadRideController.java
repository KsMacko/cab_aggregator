package com.intership.ride_service.controller;

import com.intership.ride_service.dto.PromoCodeDto;
import com.intership.ride_service.dto.RideDto;
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
import java.util.List;

@RestController
@RequestMapping("/api/ride")
@RequiredArgsConstructor
public class ReadRideController {

    private final ReadRideService readRideService;

    @GetMapping("/read-by-id")
    public RideDto getRideById(@RequestParam String id) {
        return readRideService.getRideById(id);
    }

    @GetMapping("/read-by-date")
    public List<RideDto> getAllRidesByDate(
            @RequestParam LocalDate date,
            @RequestParam(defaultValue = "startTime") String sortBy,
            @RequestParam(defaultValue = "ASC") String order) {
        return readRideService.getAllRidesByDate(date, sortBy, order);
    }

    @GetMapping("/read-by-driver")
    public List<RideDto> getAllRidesByDriver(
            @RequestParam Long driverId,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getAllRidesByDriver(driverId, sortBy, order);
    }

    @GetMapping("/read-by-passenger")
    public List<RideDto> getAllRidesByPassenger(
            @RequestParam Long passengerId,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getAllRidesByPassenger(passengerId, sortBy, order);
    }

    @GetMapping("/read-by-status")
    public List<RideDto> getAllRidesByStatus(
            @RequestParam RideStatus status,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getAllRidesByStatus(status, sortBy, order);
    }

    @GetMapping("/by-fare-type")
    public List<RideDto> getAllRidesByFareType(
            @RequestParam FareType fareType,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getAllRidesByFareType(fareType, sortBy, order);
    }

    @GetMapping("/used-promo-codes")
    public List<PromoCodeDto> getUsedPromoCodes(
            @RequestParam(defaultValue = "validUntil") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getUsedPromoCodes(sortBy, order);
    }
}
