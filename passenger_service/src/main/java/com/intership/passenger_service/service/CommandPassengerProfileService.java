package com.intership.passenger_service.service;

import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.mapper.ProfileMapper;
import com.intership.passenger_service.entity.PassengerProfile;
import com.intership.passenger_service.repo.PassengerAccountRepo;
import com.intership.passenger_service.repo.PassengerProfileRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final PassengerAccountRepo passengerAccountRepo;
    @Transactional
     public ProfileDto createNewPassengerProfile(ProfileDto profileDto) {
         PassengerProfile passengerProfile = ProfileMapper.converter.handleDto(profileDto);
         return ProfileMapper.converter.handleEntity(passengerProfileRepo.save(passengerProfile));
     }
     @Transactional
     public ProfileDto updatePassengerProfile(ProfileDto profileDto) {
         if (profileDto.profileId() == null) {
             throw new IllegalArgumentException("Passenger ID must not be null");
         }
         PassengerProfile existingProfile = passengerProfileRepo.findById(profileDto.profileId())
                 .orElseThrow(() -> new RuntimeException("Passenger not found"));
         ProfileMapper.converter.updateProfileFromDto(profileDto, existingProfile);

         return ProfileMapper.converter.handleEntity( passengerProfileRepo.save(existingProfile));
    }
    @Transactional
     public void deletePassengerProfile(Long profileId) {
         passengerAccountRepo.deleteById(profileId);
         passengerProfileRepo.deleteById(profileId);
     }
}
