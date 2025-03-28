package com.intership.passenger_service.service;

import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.mapper.ProfileMapper;
import com.intership.passenger_service.entity.PassengerProfile;
import com.intership.passenger_service.repo.PassengerAccountRepo;
import com.intership.passenger_service.repo.PassengerProfileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final PassengerAccountRepo passengerAccountRepo;

     public ProfileDto createNewPassengerProfile(ProfileDto profileDto) {
         PassengerProfile passengerProfile = ProfileMapper.convertor.handleDto(profileDto);
         return ProfileMapper.convertor.handleEntity(passengerProfileRepo.save(passengerProfile));
     }
    public ProfileDto updatePassengerProfile(ProfileDto profileDto) {
        if (profileDto.getProfileId() != null) {
            return passengerProfileRepo.findById(profileDto.getProfileId())
                    .map(profile -> {
                        if (profileDto.getFirstName() != null)
                            profile.setFirstName(profileDto.getFirstName());
                        if (profileDto.getPhone() != null)
                            profile.setPhone(profileDto.getPhone());
                        if (profileDto.getEmail() != null)
                            profile.setEmail(profileDto.getEmail());

                        return ProfileMapper.convertor.handleEntity(passengerProfileRepo.save(profile));
                    })
                    .orElse(profileDto);
        }
        return profileDto;
    }
     public void deletePassengerProfile(Long profileId) {
         passengerAccountRepo.deleteById(profileId);
         passengerProfileRepo.deleteById(profileId);
     }
}
