package com.intership.ride_service.dto.validators;

import com.intership.ride_service.config.constants.ValidationConstants;
import com.intership.ride_service.dto.PromoCodeDto;
import com.intership.ride_service.dto.validators.annotations.ValidatePromoCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ValidPromoCode extends BaseValidator implements ConstraintValidator<ValidatePromoCode, PromoCodeDto> {

    @Override
    public boolean isValid(PromoCodeDto promoCode, ConstraintValidatorContext context) {
        if (promoCode == null) {
            return true;
        }

        boolean isValid = true;
        if (!validateStringField(promoCode.code(), "code", ValidationConstants.ALPHANUMERIC_REGEX, ValidationConstants.MAX_CAR_BRAND_LENGTH, context)) {
            isValid = false;
        }
        if (promoCode.discount() == null) {
            context.buildConstraintViolationWithTemplate("discount.notNull")
                    .addPropertyNode("discount")
                    .addConstraintViolation();
            isValid = false;
        } else if (promoCode.discount() < ValidationConstants.MIN_PROMO_CODE_DISCOUNT ||
                promoCode.discount() > ValidationConstants.MAX_PROMO_CODE_DISCOUNT) {
            context.buildConstraintViolationWithTemplate("discount.min")
                    .addPropertyNode("discount")
                    .addConstraintViolation();
            isValid = false;
        }
        if (promoCode.validUntil() == null) {
            context.buildConstraintViolationWithTemplate("promo.validUntil.notNull")
                    .addPropertyNode("validUntil")
                    .addConstraintViolation();
            isValid = false;
        } else if (promoCode.validUntil().isBefore(LocalDate.now())) {
            context.buildConstraintViolationWithTemplate("promo.validUntil.invalidDate")
                    .addPropertyNode("validUntil")
                    .addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }
}