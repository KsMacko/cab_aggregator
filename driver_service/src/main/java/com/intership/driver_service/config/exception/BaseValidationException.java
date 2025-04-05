package com.intership.driver_service.config.exception;

import java.util.List;

public record BaseValidationException(String message, List<String> errors) { }