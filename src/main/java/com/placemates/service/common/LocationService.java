package com.placemates.service.common;

import com.placemates.dto.common.LocationDTO;

import java.util.List;

public interface LocationService {
    LocationDTO createLocation(LocationDTO locationDTO);
    LocationDTO getLocation(Integer id);
    List<LocationDTO> getAllLocations();
    LocationDTO updateLocation(Integer id, LocationDTO locationDTO);
    void deleteLocation(Integer id);
}
