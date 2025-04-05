package com.intership.driver_service.service;

import com.intership.driver_service.config.exception.InvalidInputException;
import com.intership.driver_service.config.exception.ResourceNotFoundException;
import com.intership.driver_service.dto.DataPackageDto;
import com.intership.driver_service.dto.DriverFilterRequest;
import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.dto.mapper.ProfileMapper;
import com.intership.driver_service.entity.DriverProfile;
import com.intership.driver_service.enums.DriverStatus;
import com.intership.driver_service.enums.FareType;
import com.intership.driver_service.repo.DriverProfileRepo;
import com.intership.driver_service.repo.RateRepo;
import com.intership.driver_service.service.specification.DriverSpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final RateRepo rateRepo;
    private final DriverSpecificationService specificationService;

    @Transactional(readOnly = true)
    public DataPackageDto readAllDrivers(DriverFilterRequest filter) {
        Pageable pageable = createPageableObject(filter);
        Specification<DriverProfile> spec = specificationService.createFilterSpecification(filter);
        Page<DriverProfile> driverProfilePage = driverProfileRepo.findAll(spec, pageable);
        return createDataPackageDto(driverProfilePage);
    }
    @Transactional(readOnly = true)
    public ProfileDto readDriverProfileById(Long id) {
        if(id==null) throw new InvalidInputException("driver.id.notNull");
        return ProfileMapper.converter.handleEntity(
                driverProfileRepo.findById(id).orElseThrow(
                        ()->new ResourceNotFoundException("driver.notFound")));
    }
    @Transactional(readOnly = true)
    public FareType readDriverProfileFareTypeById(Long driverId) {
        validateTheId(driverId);
        FareType fareType = driverProfileRepo.getFareTypeByProfileId(driverId);
        if (fareType == null) {
            throw new ResourceNotFoundException("driver.fare.notFound");
        }
        return fareType;
    }
    @Transactional(readOnly = true)
    public DriverStatus readDriverProfileStatusById(Long driverId) {
        validateTheId(driverId);
        DriverStatus status = driverProfileRepo.getDriverStatusByProfileId(driverId);
        if (status == null) {
            throw new ResourceNotFoundException("driver.status.notFound");
        }
        return status;
    }
    @Transactional(readOnly = true)
    public Byte readDriverProfileRatingById(Long driverId) {
        validateTheId(driverId);
        Byte rate = rateRepo.findDriverRatingByProfileId(driverId);
        if(rate ==null) throw new ResourceNotFoundException("driver.profile.rating.notFound");
        return rate;
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
    private Pageable createPageableObject(DriverFilterRequest filterRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(
                filterRequest.order().toString()),
                filterRequest.sortBy().getFieldName());
        return PageRequest.of(filterRequest.page(), filterRequest.size(), sort);
    }
    private void validateTheId(Long id){
        if(id==null) throw new InvalidInputException("driver.id.notNull");
        if(driverProfileRepo.findById(id).isEmpty())
            throw new ResourceNotFoundException("driver.notFound");
    }

}
