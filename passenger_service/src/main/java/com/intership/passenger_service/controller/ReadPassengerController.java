package com.intership.passenger_service.controller;

import com.intership.passenger_service.controller.doc.ReadDoc;
import com.intership.passenger_service.dto.DataPackageDto;
import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.ProfileFilterRequest;
import com.intership.passenger_service.service.ReadPassengerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/passengers")
@RequiredArgsConstructor
public class ReadPassengerController implements ReadDoc {
    private final ReadPassengerProfileService profileService;

    @GetMapping
    @Override
    public DataPackageDto readAllProfiles(ProfileFilterRequest profileFilterRequest) {
        return profileService.readPassengerProfiles(profileFilterRequest);
    }
    @GetMapping("/{id}")
    @Override
    public ProfileDto readPassengerById(@PathVariable("id") Long id) {
        return profileService.readPassengerProfile(id);
    }
    @GetMapping("/{id}/rate")
    @Override
    public Byte readRating(@PathVariable("id") Long profileId) {
        return profileService.readPassengerRating(profileId);
    }
}
