package com.intership.ride_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldToFilter {
    DATE("date"),
    START_TIME("startTime"),
    END_TIME("endTime"),
    DISTANCE("distance"),
    RIDE_STATUS("status"),
    PRICE("price");
    private final String fieldName;

}
