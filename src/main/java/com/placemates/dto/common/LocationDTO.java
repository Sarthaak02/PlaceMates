package com.placemates.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDTO {

    private Integer locationId;

    @NotBlank
    private String city;
}
