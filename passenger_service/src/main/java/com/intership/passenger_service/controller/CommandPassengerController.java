package com.intership.passenger_service.controller;

import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.service.CommandPassengerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/passenger")
@RequiredArgsConstructor
public class CommandPassengerController {
    private final CommandPassengerProfileService profileService;

    @PostMapping("/save-passenger")
    public ProfileDto createPassenger(@RequestBody ProfileDto profileDto) {
        return profileService.createNewPassengerProfile(profileDto);
    }
    @PutMapping("/update-profile")
    public ProfileDto updatePassenger(@RequestBody ProfileDto profileDto) {
        return profileService.updatePassengerProfile(profileDto);
    }
    @DeleteMapping("/delete-profile/{id}")
    public void deletePassenger(@PathVariable Long id) {
        profileService.deletePassengerProfile(id);
    }

}
