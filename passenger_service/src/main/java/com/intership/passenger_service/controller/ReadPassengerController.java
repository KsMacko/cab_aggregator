package com.intership.passenger_service.controller;

import com.intership.passenger_service.dto.DataPackageDto;
import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.service.ReadPassengerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/passengers")
@RequiredArgsConstructor
public class ReadPassengerController {
    private final ReadPassengerProfileService profileService;

    @GetMapping
    public DataPackageDto readAll(
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return profileService.readPassengersProfiles(page, size, sortBy, direction);
    }
    @GetMapping("/{id}")
    public ProfileDto readPassengerById(@PathVariable("id") Long id) {
        return profileService.readPassengerProfile(id);
    }
    @GetMapping(params = "email")
    public ProfileDto readByEmail(@RequestParam("email") String email) {
        return profileService.readPassengerProfileByEmail(email);
    }
    @GetMapping(params = "phone")
    public ProfileDto readByPhone(@RequestParam("phone") String phone) {
        return profileService.readPassengerProfileByPhone(phone);
    }
    @GetMapping("/{id}/rates")
    public Byte readRating(@PathVariable("id") Long profileId) {
        return profileService.readPassengerRating(profileId);
    }
    @GetMapping(params = "rate")
    public DataPackageDto readByRating(
            @RequestParam("rate") Byte rate,
            @RequestParam(defaultValue = "firstName") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam int page,
            @RequestParam int size) {
        return profileService.readPassengersProfilesByRating(rate, page, size, sortBy, direction);
    }
}
