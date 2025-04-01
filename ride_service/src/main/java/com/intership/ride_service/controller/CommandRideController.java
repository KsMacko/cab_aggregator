package com.intership.ride_service.controller;

import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.service.CommandRideService;
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
public class CommandRideController {
    private final CommandRideService commandRideService;
    @PostMapping
    public ResponseEntity<RideDto> createPassenger(@RequestBody RideDto rideDto) {
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
    @DeleteMapping
    public void deleteRide(@RequestParam String id) {
        commandRideService.deleteRide(id);
    }
    @PutMapping
    public RideDto updateRide(@RequestBody RideDto ride) {
        return commandRideService.updateRide(ride);
    }
}
