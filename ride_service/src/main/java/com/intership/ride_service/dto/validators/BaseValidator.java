package com.intership.ride_service.dto.validators;
import com.intership.ride_service.config.constants.ValidationConstants;
import jakarta.validation.ConstraintValidatorContext;


public abstract class BaseValidator {

    protected boolean validateStringField(String value, String fieldName, String regex, int maxLength, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            context.buildConstraintViolationWithTemplate(String.format(ValidationConstants.NOT_NULL_MESSAGE, fieldName))
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return false;
        } else if (!value.matches(regex)) {
            context.buildConstraintViolationWithTemplate(String.format(ValidationConstants.INVALID_INPUT_MESSAGE, fieldName))
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return false;
        } else if (value.length() > maxLength) {
            context.buildConstraintViolationWithTemplate(String.format(ValidationConstants.SIZE_MESSAGE, fieldName))
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
