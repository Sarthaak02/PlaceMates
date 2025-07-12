package com.placemates.service.placedalum;

import com.placemates.dto.placedalum.PlacedAlumDTO;

import java.util.List;

public interface PlacedAlumService {
    PlacedAlumDTO createPlacedAlum(PlacedAlumDTO placedAlumDTO);
    PlacedAlumDTO getPlacedAlum(Integer placedAlumId);
    List<PlacedAlumDTO> getAllPlacedAlums();
    List<PlacedAlumDTO> getAllPlacedAlumsByCompanyId(Integer companyId);
    PlacedAlumDTO updatePlacedAlum(Integer placedAlumId, PlacedAlumDTO placedAlumDTO);
    void deletePlacedAlum(Integer placedAlumId);
}
