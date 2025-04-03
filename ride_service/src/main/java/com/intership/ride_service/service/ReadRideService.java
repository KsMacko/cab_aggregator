package com.intership.ride_service.service;

import com.intership.ride_service.config.exception.ResourceNotFoundException;
import com.intership.ride_service.dto.PromoCodeDto;
import com.intership.ride_service.dto.PromoCodePackageDto;
import com.intership.ride_service.dto.RideDto;
import com.intership.ride_service.dto.transfer_objects.RideFilterRequest;
import com.intership.ride_service.dto.transfer_objects.RidePackageDto;
import com.intership.ride_service.dto.mappers.PromoCodeMapper;
import com.intership.ride_service.dto.mappers.RideMapper;
import com.intership.ride_service.entity.Ride;
import com.intership.ride_service.repo.RideRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadRideService {

    private final MongoTemplate mongoTemplate;
    private final RideRepo rideRepo;

    @Transactional(readOnly= true)
    public RidePackageDto getAllRides(RideFilterRequest filterRequest) {
        Query query = buildQuery(filterRequest);
        query.with(createPageableObject(filterRequest));

        List<RideDto> rides = mongoTemplate.find(query, Ride.class)
                .stream()
                .map(RideMapper.converter::handleEntity)
                .toList();
        long totalElements = mongoTemplate.count(query, Ride.class);

        return createRidePackage(rides, totalElements, filterRequest);
    }

    @Transactional(readOnly= true)
    public RideDto getRideById(String id) {
        Ride ride = rideRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ride.notFound"));
        return RideMapper.converter.handleEntity(ride);
    }
    @Transactional(readOnly= true)
    public PromoCodePackageDto getUsedPromoCodes(int page, int size, String sortBy, String order) {
        Page<Ride> rides = rideRepo.findByPromoCodeIsNotNull(createPageableObject(page, size, sortBy, order));
        List<PromoCodeDto> promoCodesDto = rides.getContent()
                .stream()
                .map(ride -> PromoCodeMapper.converter.handleEntity(ride.getPromoCode()))
                .toList();
        return new PromoCodePackageDto(
                promoCodesDto,
                rides.getTotalElements(),
                rides.getNumber(),
                rides.getSize(),
                rides.getTotalPages()
        );
    }
    private Query buildQuery(RideFilterRequest filterRequest) {
        Criteria criteria = new Criteria();

        if (filterRequest.date() != null) {
            criteria.and("date").is(filterRequest.date());
        }
        if (filterRequest.driverId() != null) {
            criteria.and("driver.driverId").is(filterRequest.driverId());
        }
        if (filterRequest.passengerId() != null) {
            criteria.and("passenger.passengerId").is(filterRequest.passengerId());
        }
        if (filterRequest.status() != null) {
            criteria.and("status").is(filterRequest.status());
        }
        if (filterRequest.fareType() != null) {
            criteria.and("driver.fareType").is(filterRequest.fareType());
        }

        return new Query(criteria);
    }

    private Pageable createPageableObject(RideFilterRequest filterRequest) {
        Sort sort = Sort.by(Sort.Direction.fromString(filterRequest.order()), filterRequest.sortBy());
        return PageRequest.of(filterRequest.page(), filterRequest.size(), sort);
    }
    public Pageable createPageableObject(int page, int size, String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        return PageRequest.of(page, size, sort);
    }
    public RidePackageDto createRidePackage(List<RideDto> rides, long totalElements, RideFilterRequest filterRequest) {
        return new RidePackageDto(
                rides,
                totalElements,
                filterRequest.page(),
                rides.size(),
                (int) Math.ceil((double) totalElements / filterRequest.size())
        );
    }
}