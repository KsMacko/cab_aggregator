package com.intership.ride_service.dto.validators.annotations;

import com.intership.ride_service.dto.validators.ValidDriver;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidDriver.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateDriver {
    String message() default "driver.invalidData";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
