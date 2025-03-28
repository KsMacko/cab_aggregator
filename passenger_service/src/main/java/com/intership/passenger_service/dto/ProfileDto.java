package com.intership.passenger_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private Long profileId;
    private String firstName;
    private String email;
    private String phone;
    private Float rate;
}
