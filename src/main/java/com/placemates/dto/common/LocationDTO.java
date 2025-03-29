package com.placemates.dto.common;

import jakarta.validation.constraints.NotBlank;

public class LocationDTO {

    private Integer locationId;

    @NotBlank
    private String city;

    public LocationDTO() {
    }

    public LocationDTO(Integer locationId, String city) {
        this.locationId = locationId;
        this.city = city;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
