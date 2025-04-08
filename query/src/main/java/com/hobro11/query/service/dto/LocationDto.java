package com.hobro11.query.service.dto;

import lombok.Getter;

@Getter
public class LocationDto {
    private final double latitude;
    private final double longitude;

    public LocationDto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
