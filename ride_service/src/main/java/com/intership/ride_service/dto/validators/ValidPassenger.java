package com.intership.ride_service.dto.validators;

import com.intership.ride_service.config.constants.ValidationConstants;
import com.intership.ride_service.dto.PassengerDto;
import com.intership.ride_service.dto.validators.annotations.ValidatePassenger;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPassenger extends BaseValidator implements ConstraintValidator<ValidatePassenger, PassengerDto> {

    @Override
    public boolean isValid(PassengerDto passenger, ConstraintValidatorContext context) {
        if (passenger == null) {
            return false;
        }
        boolean isValid = true;
        if (passenger.passengerId() == null || passenger.passengerId() <= 0) {
            context.buildConstraintViolationWithTemplate("passenger.id.invalidInput")
                    .addPropertyNode("passengerId")
                    .addConstraintViolation();
            isValid = false;
        }
        if (!validateStringField(
                passenger.name(),
                "name",
                ValidationConstants.CYRILLIC_REGEX,
                ValidationConstants.MAX_NAME_LENGTH,
                context)) {
            isValid = false;
        }
        return isValid;
    }
}