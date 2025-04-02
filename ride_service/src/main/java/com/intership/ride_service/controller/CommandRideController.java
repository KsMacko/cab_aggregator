package com.intership.ride_service.controller;

import com.intership.ride_service.controller.doc.CommandDoc;
import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.service.CommandRideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/rides")
@RequiredArgsConstructor
public class CommandRideController implements CommandDoc{
    private final CommandRideService commandRideService;
    @Override
    public ResponseEntity<RideDto> createPassenger(@Valid  @RequestBody RideDto rideDto) {
        RideDto createdProfile =  commandRideService.createRide(rideDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProfile.id())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(createdProfile);
    }
    @Override
    public ResponseEntity<Void> deleteRide(@RequestParam String id) {
        commandRideService.deleteRide(id);
        return ResponseEntity.noContent().build();
    }
    @Override
    public RideDto updateRide( @Valid @RequestBody RideDto ride) {
        return commandRideService.updateRide(ride);
    }
}
