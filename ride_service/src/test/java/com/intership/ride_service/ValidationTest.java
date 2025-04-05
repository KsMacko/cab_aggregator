package com.intership.ride_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
public class ValidationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testRideDtoValidation() throws Exception {
        String invalidJson = """
            {
              "passenger": {
                "passengerId": -1,
                "name": "str333ing"
              },
              "driver": {
                "driverId": -1,
                "firstName": "stri3g",
                "lastName": "st3ing",
                "fareType": "ECONOMY",
                "car": {
                  "brand": "st22ng",
                  "model": "s22ing",
                  "number": "stппппng"
                }
              },
              "promoCode": {
                "code": "striаааng",
                "discount": 555,
                "validUntil": "2020-01-01"
              },
              "startLocation": "",
              "endLocation": [],
              "startTime": "10:00",
              "endTime": "11:00",
              "distance": 15,
              "status": "COMPLETED",
              "price": 150
            }
            """;

        mockMvc.perform(post("/api/v1/rides")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors['passenger.passengerId']").value("Passenger's ID must be a positive number."))
                .andExpect(jsonPath("$.errors['driver.firstName']").value("Driver's first name must contain only Cyrillic characters."))
                .andExpect(jsonPath("$.errors['promoCode.discount']").value("Promo code's discount must not exceed 99%."));
    }
}
