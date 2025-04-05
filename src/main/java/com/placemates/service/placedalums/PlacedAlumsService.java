package com.placemates.service.placedalums;

import com.placemates.dto.placedalums.PlacedAlumsDTO;

import java.util.List;

public interface PlacedAlumsService {

    PlacedAlumsDTO createPlacedAlums(PlacedAlumsDTO placedAlumsDTO);
    PlacedAlumsDTO getPlacedAlumsById(Integer id);
    List<PlacedAlumsDTO> getAllPlacedAlumni();
    PlacedAlumsDTO updatePlacedAlumsById(Integer id, PlacedAlumsDTO placedAlumsDTO);
    void deletePlacedAlumsById(Integer id);
}
