package com.intership.driver_service.service;

import com.intership.driver_service.config.exception.InvalidInputException;
import com.intership.driver_service.config.exception.ResourceNotFoundException;
import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.dto.mapper.ProfileMapper;
import com.intership.driver_service.entity.DriverAccount;
import com.intership.driver_service.entity.DriverProfile;
import com.intership.driver_service.repo.DriverAccountRepo;
import com.intership.driver_service.repo.DriverProfileRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final DriverAccountRepo driverAccountRepo;

    @Transactional
    public ProfileDto createProfile(ProfileDto profileDto) {
        DriverProfile driverProfile = ProfileMapper.converter.handleDto(profileDto);
        DriverAccount driverAccount = driverAccountRepo.findById(profileDto.profileId())
                .orElseThrow(()->new ResourceNotFoundException("driver.account.notExists"));
        driverProfile.setDriverAccount(driverAccount);
        driverProfile.setProfileId(null);
        driverAccount.setDriverProfile(driverProfile);
        return ProfileMapper.converter.handleEntity(driverProfileRepo.save(driverProfile));
    }
    @Transactional
    public ProfileDto updateDriverProfile(ProfileDto profileDto) {
        if (profileDto.profileId() == null) {
            throw new InvalidInputException("driver.id.notNull");
        }
        DriverProfile existingProfile = driverProfileRepo
                .findById(profileDto.profileId())
                .orElseThrow(() -> new ResourceNotFoundException("driver.notFound"));
        ProfileMapper.converter.updateProfileFromDto(profileDto, existingProfile);

        return ProfileMapper.converter.handleEntity( driverProfileRepo.save(existingProfile));
    }
    @Transactional
    public void deleteDriverProfile(Long profileId) {
        if(profileId == null)
            throw new InvalidInputException("driver.id.notNull");
        if(!driverProfileRepo.existsById(profileId))
            throw new ResourceNotFoundException("driver.notFound");
        driverProfileRepo.deleteById(profileId);
        driverAccountRepo.deleteById(profileId);
    }


}
