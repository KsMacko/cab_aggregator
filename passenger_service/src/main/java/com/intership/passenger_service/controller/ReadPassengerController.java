package com.intership.passenger_service.controller;

import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.service.ReadPassengerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/passenger")
@RequiredArgsConstructor
public class ReadPassengerController {
    private final ReadPassengerProfileService profileService;

    @GetMapping("/all-passengers")
    public List<ProfileDto> readAll() {
        return profileService.readPassengersProfiles();
    }
    @GetMapping("/by-id/{id}")
    public ProfileDto readPassengerById(@PathVariable("id") Long id) {
        return profileService.readPassengerProfile(id);
    }
    @GetMapping("/by-email")
    public ProfileDto readByEmail(@RequestParam("email") String email) {
        return profileService.readPassengerProfileByEmail(email);
    }
    @GetMapping("/by-phone")
    public ProfileDto readByPhone(@RequestParam("phone") String phone) {
        return profileService.readPassengerProfileByPhone(phone);
    }
    @GetMapping("/rating/{profile_id}")
    public Byte readRating(@PathVariable("profile_id") Long profileId) {
        return profileService.readPassengerRating(profileId);
    }
    @GetMapping("/all-by-rating/{rate}")
    public List<ProfileDto> readRating(@PathVariable("rate") Byte rate) {
        return profileService.readPassengersProfilesByRating(rate);
    }



}
