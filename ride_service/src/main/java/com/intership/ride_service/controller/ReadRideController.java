package com.intership.ride_service.controller;

import com.intership.ride_service.controller.doc.ReadDoc;
import com.intership.ride_service.dto.PromoCodePackageDto;
import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.dto.transfer_objects.RideFilterRequest;
import com.intership.ride_service.dto.transfer_objects.RidePackageDto;
import com.intership.ride_service.entity.enums.FareType;
import com.intership.ride_service.entity.enums.RideStatus;
import com.intership.ride_service.service.ReadRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/rides")
@RequiredArgsConstructor
public class ReadRideController implements ReadDoc {

    private final ReadRideService readRideService;
    @Override
    public RidePackageDto getAllRides(@ModelAttribute RideFilterRequest filterRequest) {
        return readRideService.getAllRides(filterRequest);
    }

    @Override
    public RideDto getRideById(@PathVariable String id) {
        return readRideService.getRideById(id);
    }

    @Override
    public PromoCodePackageDto getUsedPromoCodes(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "validUntil") String sortBy,
            @RequestParam(defaultValue = "DESC") String order) {
        return readRideService.getUsedPromoCodes( page, size, sortBy, order);
    }
}
