package com.placemates.dto.common;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LocationDTO {

    private Integer locationId;

    @NotBlank
    private String city;
}
