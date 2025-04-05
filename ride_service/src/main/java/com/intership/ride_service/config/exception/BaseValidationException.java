package com.intership.ride_service.config.exception;

import java.util.List;

public record BaseValidationException(String message, List<String> errors) {
}
