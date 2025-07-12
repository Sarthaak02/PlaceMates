package com.placemates.service.common;

import com.placemates.dto.common.LocationDTO;

import java.util.List;

public interface LocationService {
    LocationDTO createLocation(LocationDTO locationDTO);
    LocationDTO getLocation(Integer locationId);
    List<LocationDTO> getAllLocations();
    LocationDTO updateLocation(Integer locationId, LocationDTO locationDTO);
    void deleteLocation(Integer locationId);
}
