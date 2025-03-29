package com.intership.driver_service.service;

import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.dto.mapper.ProfileMapper;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import com.intership.driver_service.repo.DriverProfileRepo;
import com.intership.driver_service.repo.RateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final RateRepo rateRepo;

    public ProfileDto readDriverProfileById(Long driverId) {
        return ProfileMapper.converter.handleEntity(
                driverProfileRepo.findById(driverId).orElseThrow());
    }
    public List<ProfileDto> readAllDriverProfiles(String sortBy, String order) {

        return driverProfileRepo.findAll(
                createSortBy(sortBy, order))
                .stream().map(ProfileMapper.converter::handleEntity).toList();
    }
    public ProfileDto readDriverProfileByPhone(String phone) {
        return ProfileMapper.converter.handleEntity(
                driverProfileRepo.findByPhone(phone));
    }
    public ProfileDto readDriverProfileByCarNumber(String carNumber) {
        return ProfileMapper.converter.handleEntity(
                driverProfileRepo.findDriverProfileByCarNumber(carNumber));
    }
    public List<ProfileDto> readDriverProfilesByFareRate(String fareType, String sortBy, String order) {
        return  driverProfileRepo.findAllByFareType(
                FareType.valueOf(fareType),
                        createSortBy(sortBy,order))
                .stream().map(ProfileMapper.converter::handleEntity).toList();
    }
    public List<ProfileDto> readDriverProfilesByDriverStatus(String driverStatus, String sortBy, String order) {
        return  driverProfileRepo.findAllByDriverStatus(
                        DriverStatus.valueOf(driverStatus),
                        createSortBy(sortBy,order))
                .stream().map(ProfileMapper.converter::handleEntity).toList();
    }
    public FareType readDriverProfileFareTypeById(Long driverId) {
        return driverProfileRepo.getFareTypeByProfileId(driverId);
    }
    public DriverStatus readDriverProfileStatusById(Long driverId) {
        return driverProfileRepo.getDriverStatusByProfileId(driverId);
    }
    public Byte readDriverProfileRatingById(Long driverId) {
        return rateRepo.findDriverRatingByProfileId(driverId);
    }
    public List<ProfileDto> readDriverProfilesByRate(Byte rate, String sortBy, String order) {
        return driverProfileRepo.findDriverProfileByRate(
                rate,createSortBy(sortBy,order))
                .stream().map(ProfileMapper.converter::handleEntity).toList();
    }

    public Sort createSortBy(String sortBy, String order) {
        Sort.Direction sortDirection = Sort.Direction.fromString(order);
        return Sort.by(sortDirection, sortBy);
    }

}
