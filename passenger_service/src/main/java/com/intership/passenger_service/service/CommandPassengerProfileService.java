package com.intership.passenger_service.service;

import com.intership.passenger_service.config.exception.InvalidInputException;
import com.intership.passenger_service.config.exception.ResourceNotFound;
import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.mapper.ProfileMapper;
import com.intership.passenger_service.entity.PassengerAccount;
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
        if (profileDto.profileId() == null) {
            throw new InvalidInputException("passenger.id.notNull");
        }
        PassengerAccount account = passengerAccountRepo.findById(profileDto.profileId())
                .orElseThrow(() -> new ResourceNotFound("passenger.account.notExists"));

        PassengerProfile passengerProfile = ProfileMapper.converter.handleDto(profileDto);
        passengerProfile.setProfileId(null);
        passengerProfile.setPassengerAccount(account);
        account.setPassengerProfile(passengerProfile);
        PassengerProfile savedProfile = passengerProfileRepo.save(passengerProfile);
        return ProfileMapper.converter.handleEntity(savedProfile);
    }
     @Transactional
     public ProfileDto updatePassengerProfile(ProfileDto profileDto) {
         if (profileDto.profileId() == null) {
             throw new InvalidInputException("passenger.id.notNull");
         }
         PassengerProfile existingProfile = passengerProfileRepo.findById(profileDto.profileId())
                 .orElseThrow(() -> new ResourceNotFound("passenger.notFound"));
         ProfileMapper.converter.updateProfileFromDto(profileDto, existingProfile);

         return ProfileMapper.converter.handleEntity( passengerProfileRepo.save(existingProfile));
    }
    @Transactional
     public void deletePassengerProfile(Long profileId) {
        if (profileId == null) {
            throw new InvalidInputException("passenger.id.notNull");
        }
        if(!passengerProfileRepo.existsById(profileId)){
            throw new ResourceNotFound("passenger.notFound");
        }
        passengerProfileRepo.deleteById(profileId);
        passengerAccountRepo.deleteById(profileId);
     }
}
