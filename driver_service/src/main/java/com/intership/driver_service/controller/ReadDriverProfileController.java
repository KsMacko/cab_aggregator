package com.intership.driver_service.controller;

import com.intership.driver_service.controller.doc.ReadDoc;
import com.intership.driver_service.dto.DataPackageDto;
import com.intership.driver_service.dto.DriverFilterRequest;
import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import com.intership.driver_service.service.ReadDriverProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class ReadDriverProfileController implements ReadDoc {

    private final ReadDriverProfileService readDriverProfileService;

    @Override
    public DataPackageDto findAllDrivers(DriverFilterRequest filter) {
        return readDriverProfileService.readAllDrivers(filter);
    }
    @Override
    public ProfileDto findById(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileById(id);
    }
    @Override
    public FareType findFareTypeByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileFareTypeById(id);
    }
    @Override
    public DriverStatus findDriverStatusByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileStatusById(id);
    }
    @Override
    public Byte findRatingByDriverId(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileRatingById(id);
    }
}
