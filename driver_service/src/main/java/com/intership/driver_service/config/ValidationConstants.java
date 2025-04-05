package com.intership.driver_service.config;

public interface ValidationConstants {
    String CYRILLIC_REGEX = "^[а-яА-ЯёЁ]+$";
    String PHONE_PATTERN = "^375(?:25|29|33|44)\\d{7}$";
    String CAR_DESCRIPTION_REGEX = "^[а-яА-ЯёЁa-zA-Z0-9 ]+$";
    String CAR_NUM_PATTERN="^[0-9]{4}[a-zA-Z]{2}[0-9]$";

    int MAX_NAME_LENGTH = 20;
    int MAX_PHONE_LENGTH = 12;
    int MAX_CAR_DESCRIPTION_LENGTH = 50;
    int MAX_CAR_NUMBER_LENGTH = 7;
    byte MIN_RATE = 1;
    byte MAX_RATE = 5;

}
