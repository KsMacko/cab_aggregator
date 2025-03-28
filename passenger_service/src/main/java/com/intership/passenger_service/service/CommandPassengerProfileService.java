package com.intership.passenger_service.service;

import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.mapper.ProfileMapper;
import com.intership.passenger_service.entity.PassengerProfile;
import com.intership.passenger_service.repo.PassengerAccountRepo;
import com.intership.passenger_service.repo.PassengerProfileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final PassengerAccountRepo passengerAccountRepo;

     public void createNewPassengerProfile(ProfileDto profileDto) {
         PassengerProfile passengerProfile = ProfileMapper.convertor.handleDto(profileDto);
         passengerProfileRepo.save(passengerProfile);
     }
     public void updatePassengerProfile(ProfileDto profileDto) {
         if(profileDto.getProfileId()!=null){
             Optional<PassengerProfile> passengerProfile = passengerProfileRepo.findById(profileDto.getProfileId());
             passengerProfile.ifPresent(profile -> {
                 if(profileDto.getFirstName()!=null)
                     profile.setFirstName(profileDto.getFirstName());
                 if(profileDto.getPhone()!=null)
                     profile.setPhone(profileDto.getPhone());
                 if(profileDto.getEmail()!=null)
                     profile.setEmail(profileDto.getEmail());
                 if(profileDto.getRate()!=null)
                     profile.setRate(profileDto.getRate());
                 passengerProfileRepo.save(profile);
             });
         }
     }
     public void deletePassengerProfile(Long profileId) {
         passengerAccountRepo.deleteById(profileId);
         passengerProfileRepo.deleteById(profileId);
     }
}
