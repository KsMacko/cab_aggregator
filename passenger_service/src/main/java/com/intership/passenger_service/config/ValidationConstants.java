package com.intership.passenger_service.config;

public interface ValidationConstants {
    String CYRILLIC_REGEX = "^[а-яА-ЯёЁ]+$";
    String PHONE_PATTERN = "^375(?:25|29|33|44)\\d{7}$";
    String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]+$";

    int MAX_NAME_LENGTH = 20;
    int MAX_PHONE_LENGTH = 12;
    int MAX_EMAIL_LENGTH = 50;

}
