package com.intership.ride_service.config.constants;


public interface ValidationConstants {

    String CYRILLIC_REGEX = "^[а-яА-ЯёЁ]+$";
    String LATIN_LETTERS_REGEX = "^[a-zA-Z]+$";
    String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]+$";

    String NOT_NULL_MESSAGE = "%s.notNull";
    String INVALID_INPUT_MESSAGE = "%s.invalidInput";
    String SIZE_MESSAGE = "%s.size";

    int MIN_PROMO_CODE_DISCOUNT = 1;
    int MAX_PROMO_CODE_DISCOUNT = 99;

    int MAX_NAME_LENGTH = 20;
    int MAX_CAR_BRAND_LENGTH = 20;
    int MAX_CAR_MODEL_LENGTH = 20;
    int MAX_CAR_NUMBER_LENGTH = 7;
    int MIN_ADDRESS_LENGTH = 10;
    int MAX_ADDRESS_LENGTH = 100;
}
