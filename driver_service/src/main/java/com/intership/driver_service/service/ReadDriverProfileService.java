package com.intership.driver_service.service;

import com.intership.driver_service.dto.DataPackageDto;
import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.dto.mapper.ProfileMapper;
import com.intership.driver_service.entity.DriverProfile;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import com.intership.driver_service.repo.DriverProfileRepo;
import com.intership.driver_service.repo.RateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public DataPackageDto readAllDriverProfiles(int page, int size, String sortBy, String direction) {
        Page<DriverProfile> driverProfilePage = driverProfileRepo.findAll(
                createPageableObject(page, size, sortBy, direction));
        return createDataPackageDto(driverProfilePage);
    }
    public ProfileDto readDriverProfileByPhone(String phone) {
        return ProfileMapper.converter.handleEntity(
                driverProfileRepo.findByPhone(phone));
    }
    public ProfileDto readDriverProfileByCarNumber(String carNumber) {
        return ProfileMapper.converter.handleEntity(
                driverProfileRepo.findDriverProfileByCarNumber(carNumber));
    }
    public DataPackageDto readDriverProfilesByFareRate(String fareType,
                                                       int page,
                                                       int size,
                                                       String sortBy,
                                                       String direction) {
        Page<DriverProfile> driverProfilePage = driverProfileRepo.findAllByFareType(
                FareType.valueOf(fareType),
                createPageableObject(page,size, sortBy, direction));
        return  createDataPackageDto(driverProfilePage);
    }
    public DataPackageDto readDriverProfilesByDriverStatus(String driverStatus,
                                                           int page,
                                                           int size,
                                                           String sortBy,
                                                           String direction) {
        Page<DriverProfile> driverProfilePage = driverProfileRepo.findAllByDriverStatus(
                DriverStatus.valueOf(driverStatus),
                createPageableObject(page, size, sortBy, direction));
        return  createDataPackageDto(driverProfilePage);
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
    public DataPackageDto readDriverProfilesByRate(Byte rate,
                                                     int page,
                                                     int size,
                                                     String sortBy,
                                                     String direction) {
        Page<DriverProfile> driverProfilePage = driverProfileRepo.findDriverProfileByRate(rate,
                createPageableObject(page, size, sortBy, direction));
        return createDataPackageDto(driverProfilePage);
    }

    public Pageable createPageableObject(int page, int size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return PageRequest.of(page, size, sort);
    }
    public DataPackageDto createDataPackageDto(Page<DriverProfile> driverProfilePage){
        List<ProfileDto> driverProfilesDto = driverProfilePage.getContent()
                .stream()
                .map(ProfileMapper.converter::handleEntity)
                .toList();
        return new DataPackageDto(
                driverProfilesDto,
                driverProfilePage.getTotalElements(),
                driverProfilePage.getNumber(),
                driverProfilePage.getSize(),
                driverProfilePage.getTotalPages()
        );
    }

}
