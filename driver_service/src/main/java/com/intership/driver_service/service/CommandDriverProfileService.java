package com.intership.driver_service.service;

import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.dto.mapper.ProfileMapper;
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
        DriverProfile driverProfile = driverProfileRepo.save(ProfileMapper.converter.handleDto(profileDto));
        return ProfileMapper.converter.handleEntity(driverProfile);
    }
    @Transactional
    public ProfileDto updateDriverProfile(ProfileDto profileDto) {
        if (profileDto.profileId() == null) {
            throw new IllegalArgumentException("Profile ID must not be null");
        }
        DriverProfile existingProfile = driverProfileRepo
                .findById(profileDto.profileId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        ProfileMapper.converter.updateProfileFromDto(profileDto, existingProfile);

        return ProfileMapper.converter.handleEntity( driverProfileRepo.save(existingProfile));
    }
    @Transactional
    public void deleteDriverProfile(Long profileId) {
        driverAccountRepo.deleteById(profileId);
        driverProfileRepo.deleteById(profileId);
    }


}
