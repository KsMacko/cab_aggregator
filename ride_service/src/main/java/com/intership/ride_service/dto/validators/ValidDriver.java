package com.intership.ride_service.dto.validators;

import com.intership.ride_service.config.constants.ValidationConstants;
import com.intership.ride_service.dto.DriverDto;
import com.intership.ride_service.dto.validators.annotations.ValidateDriver;
import com.intership.ride_service.enums.FareType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ValidDriver extends BaseValidator implements ConstraintValidator<ValidateDriver, DriverDto>{

    @Override
    public boolean isValid(DriverDto driver, ConstraintValidatorContext context) {
        if (driver == null) {
            return false;
        }

        boolean isValid = true;

        if (driver.driverId() == null || driver.driverId() <= 0) {
            context.buildConstraintViolationWithTemplate("driver.id.invalidInput")
                    .addPropertyNode("driverId")
                    .addConstraintViolation();
            isValid = false;
        }

        if (!validateStringField(
                driver.firstName(),
                "firstName",
                ValidationConstants.CYRILLIC_REGEX,
                ValidationConstants.MAX_NAME_LENGTH,
                context)) {
            isValid = false;
        }

        if (!validateStringField(
                driver.lastName(),
                "lastName",
                ValidationConstants.CYRILLIC_REGEX,
                ValidationConstants.MAX_NAME_LENGTH,
                context)) {
            isValid = false;
        }

        if (driver.fareType() == null) {
            context.buildConstraintViolationWithTemplate("driver.fare.notNull")
                    .addPropertyNode("fareType")
                    .addConstraintViolation();
            isValid = false;
        } else if (Arrays.stream(FareType.values()).noneMatch(type -> type.equals(driver.fareType()))) {
            context.buildConstraintViolationWithTemplate("driver.fare.invalidInput")
                    .addPropertyNode("fareType")
                    .addConstraintViolation();
            isValid = false;
        }

        if (driver.car() == null) {
            context.buildConstraintViolationWithTemplate("driver.car.notNull")
                    .addPropertyNode("car")
                    .addConstraintViolation();
            isValid = false;
        } else {
            if (!validateStringField(
                    driver.car().brand(),
                    "car.brand",
                    ValidationConstants.LATIN_LETTERS_REGEX,
                    ValidationConstants.MAX_CAR_BRAND_LENGTH,
                    context)) {
                isValid = false;
            }
            if (!validateStringField(
                    driver.car().model(),
                    "car.model",
                    ValidationConstants.ALPHANUMERIC_REGEX,
                    ValidationConstants.MAX_CAR_MODEL_LENGTH,
                    context)) {
                isValid = false;
            }
            if (!validateStringField(
                    driver.car().number(),
                    "car.number",
                    ValidationConstants.ALPHANUMERIC_REGEX,
                    ValidationConstants.MAX_CAR_NUMBER_LENGTH,
                    context)) {
                isValid = false;
            }
        }

        return isValid;
    }
}
