package com.intership.driver_service.service;

import com.intership.driver_service.dto.ProfileDto;
import com.intership.driver_service.dto.mapper.ProfileMapper;
import com.intership.driver_service.entity.DriverProfile;
import com.intership.driver_service.repo.DriverAccountRepo;
import com.intership.driver_service.repo.DriverProfileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final DriverAccountRepo driverAccountRepo;

    public ProfileDto createProfile(ProfileDto profileDto) {
        DriverProfile driverProfile = driverProfileRepo.save(ProfileMapper.converter.handleDto(profileDto));
        return ProfileMapper.converter.handleEntity(driverProfile);
    }
    public ProfileDto updateDriverProfile(ProfileDto profileDto) {
        if (profileDto.getProfileId() != null) {
            return driverProfileRepo.findById(profileDto.getProfileId())
                    .map(profile -> {
                        if (profileDto.getFirstName() != null)
                            profile.setFirstName(profileDto.getFirstName());
                        if(profileDto.getLastName() != null)
                            profile.setLastName(profileDto.getLastName());
                        if (profileDto.getPhone() != null)
                            profile.setPhone(profileDto.getPhone());
                        if(profileDto.getCarNumber() != null)
                            profile.setCarNumber(profileDto.getCarNumber());
                        if(profileDto.getCarDescription() != null)
                            profile.setCarDescription(profileDto.getCarDescription());
                        if(profileDto.getDriverStatus() != null)
                            profile.setDriverStatus(profileDto.getDriverStatus());
                        if(profileDto.getFareType() != null)
                            profile.setFareType(profileDto.getFareType());
                        return ProfileMapper.converter.handleEntity(driverProfileRepo.save(profile));
                    })
                    .orElse(profileDto);
        }
        return profileDto;
    }
    public void deleteDriverProfile(Long profileId) {
        driverAccountRepo.deleteById(profileId);
        driverProfileRepo.deleteById(profileId);
    }


}
