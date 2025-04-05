package com.intership.passenger_service.service;

import com.intership.passenger_service.config.exception.InvalidInputException;
import com.intership.passenger_service.config.exception.ResourceNotFound;
import com.intership.passenger_service.dto.transfer_objects.DataPackageDto;
import com.intership.passenger_service.dto.ProfileDto;
import com.intership.passenger_service.dto.transfer_objects.ProfileFilterRequest;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final RateRepo rateRepo;
    @Transactional(readOnly= true)
    public ProfileDto readPassengerProfile(Long id) {
        if(id==null) {
            throw new InvalidInputException("passenger.id.notNull");
        }
        PassengerProfile passengerProfile =  passengerProfileRepo
                .findById(id)
                .orElseThrow(()-> new ResourceNotFound("passenger.notFound"));
        return ProfileMapper.converter.handleEntity(passengerProfile);
    }

    @Transactional(readOnly= true)
    public DataPackageDto readPassengerProfiles(ProfileFilterRequest filter) {
        Pageable pageable = createPageableObject(
                filter.sortBy().getFieldName(),
                filter.order().toString(),
                filter.page(),
                filter.size());

        if (filter.email() != null) {
            return convertToDataPackageDto(passengerProfileRepo.findByEmail(filter.email(), pageable));
        } else if (filter.phone() != null) {
            return convertToDataPackageDto(passengerProfileRepo.findByPhone(filter.phone(), pageable));
        } else if (filter.rate() != null) {
            return convertToDataPackageDto(passengerProfileRepo.findPassengerProfileByRate(filter.rate(), pageable));
        } else {
            return convertToDataPackageDto(passengerProfileRepo.findAll(pageable));
        }
    }
    @Transactional(readOnly= true)
    public Byte readPassengerRating(Long passengerId) {
        if(passengerId==null) {
            throw new InvalidInputException("passenger.id.notNull");
        }
        if(!passengerProfileRepo.existsById(passengerId)){
            throw new ResourceNotFound("passenger.notFound");
        }
        return rateRepo.findPassengerRatingByProfileId(passengerId);
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
