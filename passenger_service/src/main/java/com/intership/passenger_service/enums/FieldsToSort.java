package com.intership.passenger_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldsToSort {
        FIRST_NAME("firstName"),
        EMAIL("email"),
        PHONE("phone");

        private final String fieldName;
}
