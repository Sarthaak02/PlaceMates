package com.placemates.service.placedalum;

import com.placemates.dto.placedalum.PlacedAlumDTO;

import java.util.List;

public interface PlacedAlumService {

    PlacedAlumDTO createPlacedAlum(PlacedAlumDTO placedAlumDTO);
    PlacedAlumDTO getPlacedAlum(Integer id);
    List<PlacedAlumDTO> getAllPlacedAlums();
    PlacedAlumDTO updatePlacedAlum(Integer id, PlacedAlumDTO placedAlumDTO);
    void deletePlacedAlum(Integer id);
}
