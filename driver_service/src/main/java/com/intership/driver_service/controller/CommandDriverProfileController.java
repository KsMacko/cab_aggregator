package com.intership.driver_service.controller;

import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.service.CommandDriverProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class CommandDriverProfileController {
    private final CommandDriverProfileService commandDriverProfileService;

    @PostMapping("/create-profile")
    public ProfileDto createProfile(@RequestBody ProfileDto profileDto) {
        return commandDriverProfileService.createProfile(profileDto);
    }
    @PutMapping("/update-profile")
    public ProfileDto updateProfile(@RequestBody ProfileDto profileDto) {
        return commandDriverProfileService.updateDriverProfile(profileDto);
    }
    @DeleteMapping("/delete-profile/{id}")
    public void deleteProfile(@PathVariable Long id) {
        commandDriverProfileService.deleteDriverProfile(id);
    }

}
