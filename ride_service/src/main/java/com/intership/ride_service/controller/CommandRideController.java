package com.intership.ride_service.controller;

import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.service.CommandRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ride")
@RequiredArgsConstructor
public class CommandRideController {
    private final CommandRideService commandRideService;
    @PostMapping("/create-ride")
    public RideDto createRide(@RequestBody RideDto ride) {
        return commandRideService.createRide(ride);
    }
    @DeleteMapping("/delete-ride")
    public void deleteRide(@RequestParam String id) {
        commandRideService.deleteRide(id);
    }
    @PutMapping("/update-ride")
    public RideDto updateRide(@RequestBody RideDto ride) {
        return commandRideService.updateRide(ride);
    }
}
