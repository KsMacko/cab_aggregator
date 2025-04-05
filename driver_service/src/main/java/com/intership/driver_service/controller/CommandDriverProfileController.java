package com.intership.driver_service.controller;

import com.intership.driver_service.controller.doc.CommandDoc;
import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.service.CommandDriverProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class CommandDriverProfileController implements CommandDoc {
    private final CommandDriverProfileService commandDriverProfileService;

    @Override
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        ProfileDto createdProfile = commandDriverProfileService.createProfile(profileDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProfile.profileId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(createdProfile);
    }
    @Override
    public ProfileDto updateProfile(@RequestBody ProfileDto profileDto) {
        return commandDriverProfileService.updateDriverProfile(profileDto);
    }
    @Override
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        commandDriverProfileService.deleteDriverProfile(id);
        return ResponseEntity.noContent().build();
    }

}
