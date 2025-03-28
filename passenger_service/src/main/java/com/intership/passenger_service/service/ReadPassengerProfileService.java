package com.intership.passenger_service.service;

import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.mapper.ProfileMapper;
import com.intership.passenger_service.entity.PassengerProfile;
import com.intership.passenger_service.repo.PassengerProfileRepo;
import com.intership.passenger_service.repo.RateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final RateRepo rateRepo;

    public ProfileDto readPassengerProfile(Long id) {
        PassengerProfile passengerProfile =  passengerProfileRepo.findById(id).orElseThrow();
        return ProfileMapper.convertor.handleEntity(passengerProfile);
    }
    public ProfileDto readPassengerProfileByPhone(String phone) {
        PassengerProfile passengerProfile =  passengerProfileRepo.findByPhone(phone).orElseThrow();
        return ProfileMapper.convertor.handleEntity(passengerProfile);
    }
    public ProfileDto readPassengerProfileByEmail(String email) {
        PassengerProfile passengerProfile =  passengerProfileRepo.findByEmail(email).orElseThrow();
        return ProfileMapper.convertor.handleEntity(passengerProfile);
    }
    public List<ProfileDto> readPassengersProfiles() {
        return passengerProfileRepo.findAll()
                .stream().map(ProfileMapper.convertor::handleEntity).toList();
    }
    public Byte readPassengerRating(Long passengerId) {
        return rateRepo.findPassengerRatingByProfileId(passengerId);
    }
    public List<ProfileDto> readPassengersProfilesByRating(Byte rate) {
        return passengerProfileRepo.findPassengerProfileByRate(rate)
                .stream().map(ProfileMapper.convertor::handleEntity).toList();
    }


}
