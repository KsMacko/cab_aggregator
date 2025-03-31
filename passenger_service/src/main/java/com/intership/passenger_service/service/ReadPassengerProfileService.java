package com.intership.passenger_service.service;

import com.intership.passenger_service.dto.DataPackageDto;
import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.mapper.ProfileMapper;
import com.intership.passenger_service.entity.PassengerProfile;
import com.intership.passenger_service.repo.PassengerProfileRepo;
import com.intership.passenger_service.repo.RateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final RateRepo rateRepo;

    public ProfileDto readPassengerProfile(Long id) {
        PassengerProfile passengerProfile =  passengerProfileRepo
                .findById(id)
                .orElseThrow();
        return ProfileMapper.converter.handleEntity(passengerProfile);
    }
    public ProfileDto readPassengerProfileByPhone(String phone) {
        PassengerProfile passengerProfile =  passengerProfileRepo
                .findByPhone(phone)
                .orElseThrow();
        return ProfileMapper.converter.handleEntity(passengerProfile);
    }
    public ProfileDto readPassengerProfileByEmail(String email) {
        PassengerProfile passengerProfile =  passengerProfileRepo
                .findByEmail(email)
                .orElseThrow();
        return ProfileMapper.converter.handleEntity(passengerProfile);
    }
    public DataPackageDto readPassengersProfiles(int page, int size, String sortBy, String direction) {
        Pageable pageable = createPageableObject(sortBy, direction, page, size);
        return convertToDataPackageDto(
                passengerProfileRepo.findAll(pageable)
        );
    }
    public Byte readPassengerRating(Long passengerId) {
        return rateRepo.findPassengerRatingByProfileId(passengerId);
    }
    public DataPackageDto readPassengersProfilesByRating(Byte rate, int page, int size, String sortBy, String direction) {
        Pageable pageable = createPageableObject(sortBy, direction, page, size);
        return convertToDataPackageDto(
                passengerProfileRepo.findPassengerProfileByRate(rate, pageable)
        );
    }

    public Pageable createPageableObject( String sortBy, String direction, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return PageRequest.of(page, size, sort);
    }
    public DataPackageDto convertToDataPackageDto(Page<PassengerProfile> profilePage){
        List<ProfileDto> profilesDto = profilePage.getContent()
                .stream()
                .map(ProfileMapper.converter::handleEntity)
                .toList();
        return new DataPackageDto(
                profilesDto,
                profilePage.getTotalElements(),
                profilePage.getNumber(),
                profilePage.getSize(),
                profilePage.getTotalPages()
        );
    }


}
