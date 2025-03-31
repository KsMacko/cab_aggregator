package com.intership.passenger_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String yamlFilePath = getClass().getClassLoader().getResource("openapi.yaml").toString();
        return new OpenAPIV3Parser().read(yamlFilePath);
    }
}