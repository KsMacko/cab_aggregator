package com.intership.ride_service.dto.validators.annotations;

import com.intership.ride_service.dto.validators.ValidPromoCode;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidPromoCode.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidatePromoCode {
    String message() default "promo.code.invalidInput";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
